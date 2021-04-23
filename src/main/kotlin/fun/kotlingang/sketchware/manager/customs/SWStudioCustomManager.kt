package `fun`.kotlingang.sketchware.manager.customs

import `fun`.kotlingang.sketchware.interfaces.CustomMenusManager
import `fun`.kotlingang.sketchware.editors.customs.SWStudioCustomMenusEditor
import java.io.File

class SWStudioCustomManager(
    override val dataSystemFolder: File,
    override val dataSettingsFile: File,
    override val menuFolder: File
) : SWProCustomManager(dataSystemFolder, dataSettingsFile, menuFolder) {

    private var menusManager: SWStudioCustomMenusEditor? = null

    override suspend fun getMenusManager(): CustomMenusManager {
        menusManager = menusManager ?: SWStudioCustomMenusEditor(
            File(menuFolder, "menu.json")
        )
        return menusManager ?: error("menusManager should be initialized.")
    }
}