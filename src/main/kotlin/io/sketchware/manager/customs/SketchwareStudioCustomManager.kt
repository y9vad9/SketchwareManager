package io.sketchware.manager.customs

import io.sketchware.interfaces.CustomMenusManager
import io.sketchware.manager.customs.menus.SketchwareStudioCustomMenusManager
import java.io.File

class SketchwareStudioCustomManager(
    override val dataSystemFolder: File,
    override val dataSettingsFile: File,
    override val menuFolder: File
) : SketchwareProCustomManager(dataSystemFolder, dataSettingsFile, menuFolder) {

    private var menusManager: SketchwareStudioCustomMenusManager? = null

    override suspend fun getMenusManager(): CustomMenusManager {
        menusManager = menusManager ?: SketchwareStudioCustomMenusManager(
            File(menuFolder, "menu.json")
        )
        return menusManager ?: error("menusManager should be initialized.")
    }
}