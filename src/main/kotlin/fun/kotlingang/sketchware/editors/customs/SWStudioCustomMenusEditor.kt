package `fun`.kotlingang.sketchware.editors.customs

import `fun`.kotlingang.sketchware.interfaces.managers.CustomMenusManager
import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.objects.customs.SWProMenu
import `fun`.kotlingang.sketchware.objects.customs.SWStudioMenu
import `fun`.kotlingang.sketchware.internal.delegates.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SWStudioCustomMenusEditor(
    private var menusValue: String,
    private val menusFile: File
) : CustomMenusManager, Editor, CoroutineScope {

    companion object {
        suspend operator fun invoke(menusFile: File) =
            SWStudioCustomMenusEditor(menusFile.read().bytesToString(), menusFile)
    }

    private val menusProperty = lazyResetable {
        menusValue.serialize<List<SWStudioMenu>>().map {
            SWProMenu(it.name, it.name, it.title, it.data)
        }
    }

    /**
     * @return list of custom menus.
     */
    override val menus by menusProperty

    /**
     * Adds custom menu.
     * @param menu menu to add
     */
    override fun addCustomMenu(menu: SWProMenu) = saveCustomMenus(
        menus.toMutableList().also { it.add(menu) }
    )

    /**
     * Removes custom menu by id.
     * @param id menu's string id.
     */
    override fun removeMenuById(id: String) = saveCustomMenus(
        menus.toMutableList().also { menus ->
            menus.removeIf { it.id == id }
        }
    )

    /**
     * Edits custom menu.
     * @param id menu string id.
     * @param builder Lambda with [SWProMenu] in context to edit already exists menu data.
     */
    override fun editMenu(id: String, builder: SWProMenu.() -> Unit) = editMenu(
        id, menus.toMutableList().first { it.id == id }.apply(builder)
    )

    /**
     * Edits custom menu.
     * @param id menu string id.
     * @param menu new menu data.
     */
    override fun editMenu(id: String, menu: SWProMenu) = saveCustomMenus(
        menus.toMutableList().apply {
            val oldMenu = first { it.id == id }
            set(indexOf(oldMenu), menu)
        }
    )

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified or it is returning same value as specified
     * in [conflictProvider#conflicId],prime menu will be removed.
     */
    override suspend fun import(file: File, conflictProvider: ((conflictId: String) -> String)?) {
        val newMenus = file.read().bytesToString().serialize<List<SWProMenu>>()
        val allMenus = menus.toMutableList()
        newMenus.forEach { menu ->
            allMenus.find { it.id == menu.id }?.let {
                val currentId = it.id
                it.id = conflictProvider?.invoke(currentId) ?: currentId
                if (currentId == it.id)
                    allMenus.remove(it)
            }
        }
        menusProperty.reset()
        saveCustomMenus(allMenus.plus(newMenus))
    }

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified or it is returning same value as specified
     * in [conflictProvider#conflicId],prime menu will be removed.
     * @param callback - will be called when import will be finished.
     */
    override fun import(
        file: File,
        conflictProvider: ((conflictId: String) -> String)?,
        callback: ActionFinishListener
    ) = launch {
        import(file, conflictProvider)
        callback.onFinish()
    }

    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     */
    override suspend fun export(destination: File) {
        destination.write(menus.deserialize().toByteArray())
    }

    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     * @param callback - call back when export will be finished.
     */
    override fun export(destination: File, callback: ActionFinishListener) = launch {
        export(destination)
        callback.onFinish()
    }

    private fun saveCustomMenus(list: List<SWProMenu>) {
        menusValue = list.map {
            it.toSWStudioMenu()
        }.deserialize()
        menusProperty.reset()
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
        menusValue = menusFile.read().bytesToString()
        menusProperty.reset()
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
        menusFile.write(menusValue.toByteArray())
    }

    override val coroutineContext = Dispatchers.IO
}