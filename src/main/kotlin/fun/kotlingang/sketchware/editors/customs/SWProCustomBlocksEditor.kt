package `fun`.kotlingang.sketchware.editors.customs

import `fun`.kotlingang.sketchware.interfaces.Editor
import `fun`.kotlingang.sketchware.interfaces.FileExportable
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.freeBetweenOrDefault
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.objects.customs.CustomBlock
import `fun`.kotlingang.sketchware.objects.customs.CustomBlockGroup
import `fun`.kotlingang.sketchware.objects.customs.Palette
import io.sketchware.util.delegate.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class SWProCustomBlocksEditor(
    private var blockValue: String,
    private var paletteValue: String,
    private val blockFile: File,
    private val paletteFile: File
) : CoroutineScope, Editor, FileExportable {

    companion object {
        suspend operator fun invoke(blockFile: File, paletteFile: File) =
            SWProCustomBlocksEditor(
                blockFile.read().bytesToString(), paletteFile.read().bytesToString(),
                blockFile, paletteFile
            )
    }

    override val coroutineContext = Dispatchers.IO

    private val blocksProperty = lazyResetable {
        val blocks = blockValue.serialize<List<CustomBlock>>()
        val palettes = paletteValue.serialize<List<Palette>>()
        return@lazyResetable palettes.mapIndexed { index, palette ->
            val id = index + 9 // The first group of blocks has a digital identifier with the number 9.
            CustomBlockGroup(
                id,
                palette.name,
                palette.color,
                blocks.filter { it.palette == id }
            )
        }
    }

    /**
     * @return list of custom blocks
     */
    val blocks by blocksProperty

    /**
     * Checks the availability of id between 9 (the initial identifier of any group of custom blocks)
     * and the last, if there are no free identifiers, it will return the most recent + 1.
     * @return free custom group id.
     */
    val freeId
        get() = blocks.freeBetweenOrDefault(
            9, blocks.size + 9, blocks.size + 9 + 1, transformer = CustomBlockGroup::groupId
        )

    /**
     * Resets all existing identifiers to their primary view (first group - 9, all others +1).
     * It is used to fix problems when saving (since in reality, Sketchware Pro binds blocks at index + 9,
     * when you delete a group, the blocks can move to another group). Always called in [save], [export].
     */
    fun trimIds() = saveBlocks(blocks.mapIndexed { index, customBlockGroup ->
        customBlockGroup.groupId = index + 9
        return@mapIndexed customBlockGroup
    })

    /**
     * Adds block to the resources.
     * @param blockGroup group to add.
     */
    fun addBlocksGroup(blockGroup: CustomBlockGroup) = saveBlocks(
        blocks.toMutableList().apply { add(blockGroup) }
    )

    /**
     * Removes group of blocks.
     * @param blockGroupId Custom block group id.
     */
    fun removeBlocksGroup(blockGroupId: Int) = saveBlocks(
        blocks.toMutableList().apply {
            removeIf {
                it.groupId == blockGroupId
            }
        }
    )

    /**
     * Removes block from the resources.
     * @param groupId Custom block group id.
     * @param name Custom block name.
     */
    fun removeBlock(groupId: Int, name: String) {
        val list = blocks.toMutableList()
        val group = list.first { it.groupId == groupId }
        val newBlocks = group.blocks.toMutableList().apply {
            removeIf { it.palette == groupId && it.name == name }
        }
        saveBlocks(list.apply {
            set(indexOf(group), group.copy(blocks = newBlocks))
        })
    }

    fun editBlocksGroup(groupId: Int, builder: (CustomBlockGroup) -> Unit) = saveBlocks(
        blocks.toMutableList().apply {
            val group = first { it.groupId == groupId }
            set(indexOf(group), group.apply(builder))
        }
    )

    /**
     * Adds block to specific group.
     * @param groupId group id to which block will be added.
     * @param block block to add.
     */
    fun addBlockToGroup(groupId: Int, block: CustomBlock) {
        val list = blocks.toMutableList()
        val group = list.first { it.groupId == groupId }
        val newBlocks = group.blocks.toMutableList().apply {
            add(block)
        }
        saveBlocks(list.apply {
            set(indexOf(group), group.copy(blocks = newBlocks))
        })
    }

    private fun saveBlocks(list: List<CustomBlockGroup>) {
        val allBlocks = mutableListOf<CustomBlock>()
        val allPalettes = mutableListOf<Palette>()
        list.forEach {
            allBlocks.addAll(it.blocks)
            allPalettes.add(Palette(it.hexColor, it.name))
        }
        blockValue = allBlocks.deserialize()
        paletteValue = allPalettes.deserialize()
        blocksProperty.reset()
    }

    /**
     * Updates data in Editor async.
     */
    override fun fetch(callback: ActionFinishListener?) = launch {
        fetch()
        callback?.onFinish()
    }

    /**
     * Updates data in Editor.
     */
    override suspend fun fetch() {
        blockValue = blockFile.read().bytesToString()
        paletteValue = paletteFile.read().bytesToString()
        blocksProperty.reset()
    }

    /**
     * Saves data which was edited async.
     */
    override fun save(callback: ActionFinishListener?) = launch {
        save()
        callback?.onFinish()
    }

    /**
     * Saves data which was edited.
     */
    override suspend fun save() {
        withContext(Dispatchers.Default) { trimIds() }
        blockFile.write(blockValue.toByteArray())
        paletteFile.write(paletteValue.toByteArray())
    }

    /**
     * Exports list of custom blocks into file.
     * @param destination - file to which will be written data.
     */
    override suspend fun export(destination: File) {
        withContext(Dispatchers.Default) { trimIds() }
        destination.write(blocks.deserialize().toByteArray())
    }

    /**
     * Exports list of custom blocks into file.
     * @param destination - file to which will be written data.
     * @param callback - call back when export will be finished.
     */
    override fun export(destination: File, callback: ActionFinishListener) = launch {
        export(destination)
        callback.onFinish()
    }

    /**
     * Imports data from [file].
     * @param file - File with data to import.
     */
    suspend fun import(file: File) =
        saveBlocks(
            blocks.toMutableList().plus(file.read().bytesToString()
                .serialize<List<CustomBlockGroup>>().map {
                    it.groupId = freeId
                    return@map it
                })
        ).also { blocksProperty.reset() }

    /**
     * Imports data from [file].
     * @param file - File with data to import.
     * @param callback - call back when import will be finished.
     */
    fun import(file: File, callback: ActionFinishListener) = launch {
        import(file)
        callback.onFinish()
    }
}