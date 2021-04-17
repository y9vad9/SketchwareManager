package io.sketchware.manager.customs

import io.sketchware.`interface`.CustomMenusManager
import io.sketchware.manager.customs.blocks.SWProCustomBlocksManager
import io.sketchware.manager.customs.components.SWProCustomComponentsManager
import io.sketchware.manager.customs.listeners.SWProCustomListenersManager
import io.sketchware.manager.customs.menus.SWProCustomMenusManager
import io.sketchware.model.custom.SWCustomSettings
import io.sketchware.util.internal.byteArrayToString
import io.sketchware.util.internal.readOrNull
import io.sketchware.util.internal.serialize
import java.io.File

open class SWProCustomManager(
    internal open val dataSystemFolder: File,
    internal open val dataSettingsFile: File,
    internal open val menuFolder: File
) {

    private var config: SWCustomSettings? = null

    private var componentManager: SWProCustomComponentsManager? = null
    private var blocksManager: SWProCustomBlocksManager? = null
    private var listenersManager: SWProCustomListenersManager? = null

    private var menusManager: SWProCustomMenusManager? = null

    private suspend fun getConfig(): SWCustomSettings {
        config = config ?: dataSettingsFile.readOrNull()?.byteArrayToString()?.serialize<SWCustomSettings>()
        return config ?: error("Config should be initialized.")
    }

    /**
     * @return instance of Custom Component Manager.
     */
    open suspend fun getComponentManager(): SWProCustomComponentsManager {
        componentManager = componentManager
            ?: SWProCustomComponentsManager(File(dataSystemFolder, "component.json"))
        return componentManager ?: error("componentManager should be initialized.")
    }

    /**
     * @return instance of Custom Blocks Manager.
     */
    open suspend fun getBlocksManager(): SWProCustomBlocksManager {
        blocksManager = blocksManager
            ?: SWProCustomBlocksManager(getConfig().blockFile, getConfig().paletteFile)
        return blocksManager ?: error("blocksManager should be initialized.")
    }

    open suspend fun getMenusManager(): CustomMenusManager {
        menusManager = menusManager
            ?: SWProCustomMenusManager(
                File(menuFolder, "block.json"), File(menuFolder, "data.json")
            )
        return menusManager ?: error("menusManager should be initialized")
    }

    suspend fun getListenersManager(): SWProCustomListenersManager {
        listenersManager = listenersManager
            ?: SWProCustomListenersManager(
                File(dataSystemFolder, "events.json"), File(dataSystemFolder, "listeners.json")
            )
        return listenersManager ?: error("listenersManager should be initialized")
    }

}