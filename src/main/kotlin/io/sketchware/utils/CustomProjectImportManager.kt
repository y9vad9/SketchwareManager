package io.sketchware.utils

import io.sketchware.manager.customs.SketchwareProCustomManager
import io.sketchware.manager.projects.data.FileManager
import io.sketchware.manager.projects.data.LogicManager
import io.sketchware.models.export.ExportedCustomFiles
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.utils.internal.LogicIdChanger
import io.sketchware.utils.internal.copy
import java.io.File

open class CustomProjectImportManager(
    override val locations: ProjectFilesLocations,
    override val destination: ProjectFilesLocations,
    override val projectId: Int,
    internal open val customsManager: SketchwareProCustomManager,
    internal open val exportedCustomFiles: ExportedCustomFiles,
    internal open val sourceEditedFolder: File
) : ProjectImportManager(locations, destination, projectId) {
    private var componentIdProvider: ((Int) -> Int)? = null
    private var listenerNameProvider: ((String) -> String)? = null
    private var menusConflictProvider: ((conflictId: String) -> String)? = null

    private var componentIdsChanges = mutableListOf<Pair<Int, Int>>()
    private var listenerNamesChanges = mutableListOf<Pair<String, String>>()
    private var menusNamesChanges = mutableListOf<Pair<String, String>>()

    fun setComponentConflictProvider(builder: (Int) -> Int) {
        componentIdProvider = provider@{
            val newId = builder(it)
            if (newId != it)
                componentIdsChanges.add(Pair(it, newId))
            return@provider newId
        }
    }

    fun setListenerConflictProvider(builder: (String) -> String) {
        listenerNameProvider = provider@{
            val newName = builder(it)
            if (newName != it)
                listenerNamesChanges.add(Pair(it, newName))
            return@provider newName
        }
    }

    fun setMenuConflictProvider(builder: (conflictId: String) -> String) {
        menusConflictProvider = provider@{
            val newName = builder(it)
            if (newName != it)
                menusNamesChanges.add(Pair(it, newName))
            return@provider newName
        }
    }

    private suspend fun editUniques() {
        val changer = LogicIdChanger(
            LogicManager(File(locations.dataFolder, "logic")),
            FileManager(File(locations.dataFolder, "file")).activities
        )
        if (componentIdsChanges.isNotEmpty())
            changer.editComponentIds(componentIdsChanges)
        if (listenerNamesChanges.isNotEmpty())
            changer.editEventsType(listenerNamesChanges)
        if (menusNamesChanges.isNotEmpty())
            changer.editMenuNames(menusNamesChanges)
    }

    override suspend fun import(): Unit = with(exportedCustomFiles) {
        super.import()
        customsManager.getBlocksManager().apply {
            import(blocksFile)
            save()
        }
        customsManager.getComponentManager().apply {
            import(componentsFile, componentIdProvider)
            save()
        }
        customsManager.getListenersManager().apply {
            import(listenersFile, listenerNameProvider)
            save()
        }
        customsManager.getMenusManager().apply {
            import(menusFile, menusConflictProvider)
            save()
        }
        exportedCustomFiles.sourceEditedFolder.copy(File(
            this@CustomProjectImportManager.sourceEditedFolder, "$projectId"
        ))
        editUniques()
    }

}