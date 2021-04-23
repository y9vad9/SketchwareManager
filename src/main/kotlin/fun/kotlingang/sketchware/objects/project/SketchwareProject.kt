package `fun`.kotlingang.sketchware.objects.project

import `fun`.kotlingang.sketchware.editors.project.*
import `fun`.kotlingang.sketchware.internal.extensions.*
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.removeFiles
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.internal.exceptions.ValueRequireException
import `fun`.kotlingang.sketchware.objects.project.information.ProjectConfig
import `fun`.kotlingang.sketchware.objects.project.information.ProjectFilesLocations
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.encrypt
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
        LogicEditor(File(locations.dataFolder, "logic"))
    }

    private val fileManager = async(start = CoroutineStart.LAZY) {
        ContentEditor(File(locations.dataFolder, "file"))
    }

    private val libraryManager = async(start = CoroutineStart.LAZY) {
        LibraryEditor(File(locations.dataFolder, "library"))
    }

    private val viewManager = async(start = CoroutineStart.LAZY) {
        ViewEditor(File(locations.dataFolder, "view"))
    }

    private val resourcesManager = async(start = CoroutineStart.LAZY) {
        ResourcesEditor(File(locations.dataFolder, "resource"))
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
     * Returns config with data about sketchware project.
     * @return [ProjectConfig] with data about project.
     * @see ProjectConfig
     */
    open suspend fun getConfig() =
        locations.projectFile.read().decrypt().bytesToString().serialize<ProjectConfig>()

    /**
     * Edits sketchware project config.
     * @param editor lambda with [ProjectConfig] in context.
     */
    open suspend fun editConfig(editor: ProjectConfig.() -> Unit) =
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