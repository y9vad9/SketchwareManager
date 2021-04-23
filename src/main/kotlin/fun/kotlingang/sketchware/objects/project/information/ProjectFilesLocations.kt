package `fun`.kotlingang.sketchware.objects.project.information

import java.io.File

/**
 * Class with data about files where contains data/resources for some project.
 */
open class ProjectFilesLocations(
    /**
     * Project directory where "project" file located.
     */
    val projectMainDirectory: File? = null,
    /**
     * File with data about project.
     * @see ProjectConfig to see what info file contains.
     */
    val projectFile: File,
    /**
     * Folder with data about logic, view, project activities / custom views, etc.
     */
    val dataFolder: File,
    val resources: ProjectResourcesFiles
) {
    companion object {
        fun getSWDefault(swRootFolder: File, id: Int) = ProjectFilesLocations(
            File(swRootFolder, "mysc/list/$id"), File(swRootFolder, "mysc/list/$id/project"),
            File(swRootFolder, "data/$id"), ProjectResourcesFiles(
                File(swRootFolder, "resources/icons/$id"), File(swRootFolder, "resources/images/$id"),
                File(swRootFolder, "resources/sounds/$id"), File(swRootFolder, "resources/fonts/$id")
            )
        )

        fun getDefaultExport(rootFolder: File) = ProjectFilesLocations(
            null, File(rootFolder, "project"),
            File(rootFolder, "data"), ProjectResourcesFiles(
                File(rootFolder, "resources/icons"), File(rootFolder, "resources/images"),
                File(rootFolder, "resources/sounds"), File(rootFolder, "resources/fonts")
            )
        )
    }
}

/**
 * Class with data about project resources.
 */
open class ProjectResourcesFiles(
    /**
     * Folder with all app icons (usually there only one icon with name app_icon.png).
     */
    open val iconsFolder: File,
    /**
     * Folder with project's images.
     */
    open val imagesFolder: File,
    /**
     * Folder with project's sounds
     */
    open val soundsFolder: File,
    /**
     * Folder with project's fonts.
     */
    open val fontsFolder: File
)
