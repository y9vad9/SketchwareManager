package `fun`.kotlingang.sketchware.objects.project

import `fun`.kotlingang.sketchware.objects.project.information.ProjectFilesLocations
import `fun`.kotlingang.sketchware.internal.extensions.getFilesOrEmpty
import java.io.File

open class SketchwareProProject(
    override val locations: ProjectFilesLocations,
    internal open val sourceEditedFolder: File
) : SketchwareProject(locations) {

    /**
     * Returns files which was edited.
     * @return list with files.
     */
    suspend fun getSourceEditedFiles() =
        sourceEditedFolder.getFilesOrEmpty()

    /**
     * Returns files from assets directory
     * @return list with files.
     */
    suspend fun getAssets() = File(locations.dataFolder, "files/assets").getFilesOrEmpty()

    /**
     * Returns list of files with background services.
     * @return list with '.java' files.
     */
    suspend fun getBackgroundServices() =
        File(locations.dataFolder, "files/broadcast").getFilesOrEmpty()

    /**
     * Returns instance of [Resources] to access with project resources.
     */
    fun getResources() = Resources(File(locations.dataFolder, "files/resource"))

    class Resources internal constructor(private val resourceFolder: File) {
        /**
         * Gets list of files in /files/resource/anim folder.
         * @return xml files with animations.
         */
        suspend fun getAnimations() = File(resourceFolder, "anim").getFilesOrEmpty()

        /**
         * Gets list of xml files in /files/resource/layout folder.
         * @return xml files with animations.
         */
        suspend fun getLayouts() = File(resourceFolder, "layout").getFilesOrEmpty()
    }

}