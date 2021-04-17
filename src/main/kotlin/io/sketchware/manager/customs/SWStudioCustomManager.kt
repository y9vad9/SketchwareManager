package io.sketchware.manager.customs

import io.sketchware.`interface`.CustomMenusManager
import io.sketchware.manager.customs.menus.SWStudioCustomMenusManager
import java.io.File

class SWStudioCustomManager(
    override val dataSystemFolder: File,
    override val dataSettingsFile: File,
    override val menuFolder: File
) : SWProCustomManager(dataSystemFolder, dataSettingsFile, menuFolder) {

    private var menusManager: SWStudioCustomMenusManager? = null

    override suspend fun getMenusManager(): CustomMenusManager {
        menusManager = menusManager ?: SWStudioCustomMenusManager(
            File(menuFolder, "menu.json")
        )
        return menusManager ?: error("menusManager should be initialized.")
    }
}