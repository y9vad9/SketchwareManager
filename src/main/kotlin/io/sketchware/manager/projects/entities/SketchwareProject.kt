package io.sketchware.manager.projects.entities

import io.sketchware.exceptions.ValueRequireException
import io.sketchware.manager.exporters.ProjectExporter
import io.sketchware.manager.projects.data.*
import io.sketchware.models.projects.ProjectConfigModel
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.utils.SketchwareEncryptor.decrypt
import io.sketchware.utils.SketchwareEncryptor.encrypt
import io.sketchware.utils.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File

open class SketchwareProject(
    open val locations: ProjectFilesLocations
) : CoroutineScope {

    override val coroutineContext = Dispatchers.Default

    private val logicManager = async(start = CoroutineStart.LAZY) {
        LogicManager(File(locations.dataFolder, "logic"))
    }

    private val fileManager = async(start = CoroutineStart.LAZY) {
        FileManager(File(locations.dataFolder, "file"))
    }

    private val libraryManager = async(start = CoroutineStart.LAZY) {
        LibraryManager(File(locations.dataFolder, "library"))
    }

    private val viewManager = async(start = CoroutineStart.LAZY) {
        ViewManager(File(locations.dataFolder, "view"))
    }

    private val resourcesManager = async(start = CoroutineStart.LAZY) {
        ResourcesManager(File(locations.dataFolder, "resource"))
    }


    suspend fun getLogicManager() = logicManager.await()
    suspend fun getFileManager() = fileManager.await()
    suspend fun getLibraryManager() = libraryManager.await()
    suspend fun getViewManager() = viewManager.await()
    suspend fun getResourcesManager() = resourcesManager.await()

    /**
     * Copies project to specified locations in [newLocations].
     * @param newProjectId - new project id (config will be overwritten)
     * @param newLocations - new project locations.
     */
    open suspend fun copy(newProjectId: Int, newLocations: ProjectFilesLocations) = with(locations) {
        dataFolder.copy(newLocations.dataFolder)
        projectMainDirectory?.copy(
            locations.projectMainDirectory
                ?: throw ValueRequireException(
                    "newLocations.projectMainDirectory",
                    "You should specify [projectMainDirectory] due to it exists in [locations]"
                )
        )
        projectFile.copy(locations.projectFile)
        resources.soundsFolder.copy(newLocations.resources.soundsFolder)
        resources.fontsFolder.copy(newLocations.resources.fontsFolder)
        resources.iconsFolder.copy(newLocations.resources.iconsFolder)
        resources.imagesFolder.copy(newLocations.resources.iconsFolder)
    }

    /**
     * Simple project exporter.
     * Will not export custom settings or anything other than project files.
     */
    open val simpleExporter by lazy { ProjectExporter(locations) }

    /**
     * Returns config with data about sketchware project.
     * @return [ProjectConfigModel] with data about project.
     * @see ProjectConfigModel
     */
    open suspend fun getConfig() =
        locations.projectFile.read().decrypt().byteArrayToString().serialize<ProjectConfigModel>()

    /**
     * Edits sketchware project config.
     * @param editor lambda with [ProjectConfigModel] in context.
     */
    open suspend fun editConfig(editor: ProjectConfigModel.() -> Unit) =
        locations.projectFile.write(
            getConfig().apply(editor).deserialize().toByteArray().encrypt()
        )

    /**
     * Deletes all project files.
     */
    open suspend fun delete() {
        locations.dataFolder.remove()
        locations.projectFile.remove()
        locations.projectMainDirectory?.remove()
        locations.resources.apply {
            removeFiles(imagesFolder, iconsFolder, fontsFolder, soundsFolder)
        }
    }
}

internal fun SketchwareProject.toSketchwareProProject(sourceEditedFolder: File) =
    SketchwareProProject(locations, sourceEditedFolder)

internal fun SketchwareProject.toSketchwareStudioProject(sourceEditedFolder: File) =
    SketchwareStudioProject(locations, sourceEditedFolder)