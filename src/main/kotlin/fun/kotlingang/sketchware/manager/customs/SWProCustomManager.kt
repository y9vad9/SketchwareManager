package `fun`.kotlingang.sketchware.manager.customs

import `fun`.kotlingang.sketchware.editors.customs.SWProCustomBlocksEditor
import `fun`.kotlingang.sketchware.editors.customs.SWProCustomComponentsEditor
import `fun`.kotlingang.sketchware.editors.customs.SWProCustomListenersEditor
import `fun`.kotlingang.sketchware.editors.customs.SWProCustomMenusEditor
import `fun`.kotlingang.sketchware.interfaces.managers.CustomMenusManager
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.readOrNull
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.objects.customs.SWCustomSettings
import java.io.File

open class SWProCustomManager(
    internal open val dataSystemFolder: File,
    internal open val dataSettingsFile: File,
    internal open val menuFolder: File
) {

    private var config: SWCustomSettings? = null

    private var componentEditor: SWProCustomComponentsEditor? = null
    private var blocksEditor: SWProCustomBlocksEditor? = null
    private var listenersEditor: SWProCustomListenersEditor? = null

    private var menusEditor: SWProCustomMenusEditor? = null

    private suspend fun getConfig(): SWCustomSettings {
        config = config ?: dataSettingsFile.readOrNull()?.bytesToString()?.serialize<SWCustomSettings>()
        return config ?: error("Config should be initialized.")
    }

    /**
     * @return instance of Custom Component SketchwareManager.
     */
    open suspend fun getComponentManager(): SWProCustomComponentsEditor {
        componentEditor = componentEditor
            ?: SWProCustomComponentsEditor(File(dataSystemFolder, "component.json"))
        return componentEditor ?: error("componentEditor should be initialized.")
    }

    /**
     * @return instance of Custom Blocks SketchwareManager.
     */
    open suspend fun getBlocksManager(): SWProCustomBlocksEditor {
        blocksEditor = blocksEditor
            ?: SWProCustomBlocksEditor(getConfig().blockFile, getConfig().paletteFile)
        return blocksEditor ?: error("blocksEditor should be initialized.")
    }

    open suspend fun getMenusManager(): CustomMenusManager {
        menusEditor = menusEditor
            ?: SWProCustomMenusEditor(
                File(menuFolder, "block.json"), File(menuFolder, "data.json")
            )
        return menusEditor ?: error("menusEditor should be initialized")
    }

    suspend fun getListenersManager(): SWProCustomListenersEditor {
        listenersEditor = listenersEditor
            ?: SWProCustomListenersEditor(
                File(dataSystemFolder, "events.json"), File(dataSystemFolder, "listeners.json")
            )
        return listenersEditor ?: error("listenersEditor should be initialized")
    }

}