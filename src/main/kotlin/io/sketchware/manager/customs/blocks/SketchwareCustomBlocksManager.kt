package io.sketchware.manager.customs.blocks

import io.sketchware.interfaces.Editor
import io.sketchware.interfaces.FileExportable
import io.sketchware.interfaces.FileImportable
import io.sketchware.interfaces.listeners.ActionFinishListener
import io.sketchware.models.customs.CustomBlock
import io.sketchware.models.customs.CustomBlockGroup
import io.sketchware.models.customs.Palette
import io.sketchware.utils.delegates.lazyInit
import io.sketchware.utils.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SketchwareCustomBlocksManager(
    private var blockValue: String,
    private var paletteValue: String,
    private val blockFile: File,
    private val paletteFile: File
) : CoroutineScope, Editor, FileExportable, FileImportable {

    companion object {
        suspend operator fun invoke(blockFile: File, paletteFile: File) =
            SketchwareCustomBlocksManager(
                blockFile.read().byteArrayToString(), paletteFile.read().byteArrayToString(),
                blockFile, paletteFile
            )
    }

    override val coroutineContext = Dispatchers.IO

    private val blocksProperty = lazyInit {
        val blocks = blockValue.serialize<List<CustomBlock>>()
        val palettes = paletteValue.serialize<List<Palette>>()
        return@lazyInit palettes.mapIndexed { index, palette ->
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

    val freeId get() = blocks.maxOf { it.groupId } + 1

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

    fun editBlocksGroup(groupId: Int, builder: CustomBlockGroup.() -> Unit) = saveBlocks(
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
        blockValue = blockFile.read().byteArrayToString()
        paletteValue = paletteFile.read().byteArrayToString()
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
        blockFile.write(blockValue.toByteArray())
        paletteFile.write(paletteValue.toByteArray())
    }

    /**
     * Exports list of custom blocks into file.
     * @param destination - file to which will be written data.
     */
    override suspend fun export(destination: File) {
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
    override suspend fun import(file: File) =
        saveBlocks(
            blocks.toMutableList().plus(file.read().byteArrayToString()
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
    override fun import(file: File, callback: ActionFinishListener) = launch {
        import(file)
        callback.onFinish()
    }
}