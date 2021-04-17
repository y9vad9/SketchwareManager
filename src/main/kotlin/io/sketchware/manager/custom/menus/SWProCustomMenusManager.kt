package io.sketchware.manager.custom.menus

import io.sketchware.`interface`.CustomMenusManager
import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.model.custom.BlockInputMenu
import io.sketchware.model.custom.CustomMenu
import io.sketchware.model.custom.MenuData
import io.sketchware.util.delegate.lazyResetable
import io.sketchware.util.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SWProCustomMenusManager(
    private var menuBlockValue: String,
    private var menuDataValue: String,
    private val menuBlockFile: File,
    private val menuDataFile: File
) : CustomMenusManager, Editor, CoroutineScope {

    companion object {
        suspend operator fun invoke(menuBlockFile: File, menuDataFile: File) =
            SWProCustomMenusManager(
                menuBlockFile.read().byteArrayToString(), menuDataFile.read().byteArrayToString(),
                menuBlockFile, menuDataFile
            )
    }

    private val menusProperty = lazyResetable {
        val menus = menuBlockValue.serialize<List<BlockInputMenu>>()
        val menusData = menuDataValue.serialize<Map<String, List<MenuData>>>()

        return@lazyResetable menusData.keys.toList().map { key ->
            val menu = menusData[key]!!
            val values = menu.map { it.value.split("+") }.flatten()

            return@map CustomMenu(
                menus.map(BlockInputMenu::id).first(key::equals),
                menus.find { key == it.id }?.name
                    ?: error("Unexpected error while getting custom menu. Name is unspecified."),
                menu[0].title,
                values
            )
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
    override fun addCustomMenu(menu: CustomMenu) = saveCustomMenus(
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
     * @param builder Lambda with [CustomMenu] in context to edit already exists menu data.
     */
    override fun editMenu(id: String, builder: CustomMenu.() -> Unit) = editMenu(
        id, menus.toMutableList().first { it.id == id }.apply(builder)
    )

    /**
     * Edits custom menu.
     * @param id menu string id.
     * @param menu new menu data.
     */
    override fun editMenu(id: String, menu: CustomMenu) = saveCustomMenus(
        menus.toMutableList().apply {
            val oldMenu = first { it.id == id }
            set(indexOf(oldMenu), menu)
        }
    )

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified, prime menu will be removed.
     */
    override suspend fun import(file: File, conflictProvider: ((conflictId: String) -> String)?) {
        val newMenus = file.read().byteArrayToString().serialize<List<CustomMenu>>()
        val allMenus = menus.toMutableList()
        newMenus.forEach { menu ->
            allMenus.find { it.id == menu.id }?.let {
                val currentId = it.id
                it.id = conflictProvider?.invoke(currentId) ?: currentId
                if (currentId == it.id)
                    allMenus.remove(it)
            }
        }
        saveCustomMenus(allMenus.plus(newMenus))
        menusProperty.reset()
    }

    /**
     * Imports custom menus from the [file].
     * @param file - file with data about menus.
     * @param conflictProvider - provider for conflict names.
     * If provider isn't specified, prime menu will be removed.
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

    private fun saveCustomMenus(list: List<CustomMenu>) {
        val blocks = mutableListOf<BlockInputMenu>()
        val dataList = mutableListOf<MutableMap<String, List<MenuData>>>()
        list.forEach {
            blocks.add(BlockInputMenu(it.id, it.name))
            val data = mutableMapOf<String, List<MenuData>>()
            data[it.id] = listOf(MenuData(it.title, it.options.joinToString("+")))
            dataList.add(data)
        }
        menuBlockValue = blocks.deserialize()
        menuDataValue = dataList.deserialize()
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
        menuBlockValue = menuBlockFile.read().byteArrayToString()
        menuDataValue = menuDataFile.read().byteArrayToString()
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
        menuBlockFile.write(menuBlockValue.toByteArray())
        menuDataFile.write(menuDataValue.toByteArray())
    }

    override val coroutineContext = Dispatchers.IO
}