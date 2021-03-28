package io.sketchware.manager.customs

import io.sketchware.interfaces.CustomMenusManager
import io.sketchware.manager.customs.blocks.SketchwareCustomBlocksManager
import io.sketchware.manager.customs.components.SketchwareCustomComponentsManager
import io.sketchware.manager.customs.listeners.SketchwareCustomListenersManager
import io.sketchware.manager.customs.menus.SketchwareProCustomMenusManager
import io.sketchware.models.customs.SWCustomSettings
import io.sketchware.utils.internal.byteArrayToString
import io.sketchware.utils.internal.readOrNull
import io.sketchware.utils.internal.serialize
import java.io.File

open class SketchwareProCustomManager(
    internal open val dataSystemFolder: File,
    internal open val dataSettingsFile: File,
    internal open val menuFolder: File
) {

    private var config: SWCustomSettings? = null

    private var componentManager: SketchwareCustomComponentsManager? = null
    private var blocksManager: SketchwareCustomBlocksManager? = null
    private var listenersManager: SketchwareCustomListenersManager? = null

    private var menusManager: SketchwareProCustomMenusManager? = null

    private suspend fun getConfig(): SWCustomSettings {
        config = config ?: dataSettingsFile.readOrNull()?.byteArrayToString()?.serialize<SWCustomSettings>()
        return config ?: error("Config should be initialized.")
    }

    /**
     * @return instance of Custom Component Manager.
     */
    open suspend fun getComponentManager(): SketchwareCustomComponentsManager {
        componentManager = componentManager
            ?: SketchwareCustomComponentsManager(File(dataSystemFolder, "component.json"))
        return componentManager ?: error("componentManager should be initialized.")
    }

    /**
     * @return instance of Custom Blocks Manager.
     */
    open suspend fun getBlocksManager(): SketchwareCustomBlocksManager {
        blocksManager = blocksManager
            ?: SketchwareCustomBlocksManager(getConfig().blockFile, getConfig().paletteFile)
        return blocksManager ?: error("blocksManager should be initialized.")
    }

    open suspend fun getMenusManager(): CustomMenusManager {
        menusManager = menusManager
            ?: SketchwareProCustomMenusManager(
                File(menuFolder, "block.json"), File(menuFolder, "data.json")
            )
        return menusManager ?: error("menusManager should be initialized")
    }

    suspend fun getListenersManager(): SketchwareCustomListenersManager {
        listenersManager = listenersManager
            ?: SketchwareCustomListenersManager(
                File(dataSystemFolder, "events.json"), File(dataSystemFolder, "listeners.json")
            )
        return listenersManager ?: error("listenersManager should be initialized")
    }

}