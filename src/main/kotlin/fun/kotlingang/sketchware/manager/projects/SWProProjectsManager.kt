package `fun`.kotlingang.sketchware.manager.projects

import `fun`.kotlingang.sketchware.interfaces.ProjectsManager
import `fun`.kotlingang.sketchware.internal.extensions.getFilesOrEmpty
import `fun`.kotlingang.sketchware.objects.project.SketchwareProProject
import `fun`.kotlingang.sketchware.objects.project.SketchwareStudioProject
import `fun`.kotlingang.sketchware.objects.project.information.ProjectFilesLocations
import java.io.File

class SWProProjectsManager(
    private val sketchwareFolder: File
) : ProjectsManager<SketchwareProProject> {
    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))

    /**
     * Gets projects.
     * @return List of [SketchwareStudioProject].
     */
    override suspend fun getProjects() = File(sketchwareFolder, "mysc/list").getFilesOrEmpty().map {
        return@map SketchwareProProject(
            ProjectFilesLocations.getSWDefault(sketchwareFolder, it.name.toInt()),
            File(sketchwareFolder, "data/SourceEdited/${it.name.toInt()}")
        )
    }

    /**
     * Checks next free id and returns it.
     * @param startId - Specific id from which function
     * will check next free id. By default it is 601.
     * @return next free for new project id.
     */
    override suspend fun nextFreeId(startId: Int): Int {
        val files = File(sketchwareFolder, "mysc/list")
        return if (File(files, "$startId").exists())
            nextFreeId(startId + 1)
        else startId
    }

}
