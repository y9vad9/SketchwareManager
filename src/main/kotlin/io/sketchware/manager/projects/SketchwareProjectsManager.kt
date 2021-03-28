package io.sketchware.manager.projects

import io.sketchware.interfaces.ProjectsManager
import io.sketchware.manager.projects.entities.SketchwareProject
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.utils.ProjectImportManager
import io.sketchware.utils.internal.SketchwareProjectsUtil
import java.io.File

open class SketchwareProjectsManager(private val sketchwareFolder: File) : ProjectsManager<SketchwareProject> {

    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))

    /**
     * Gets projects.
     * @return List of [SketchwareProject].
     */
    override suspend fun getProjects() = SketchwareProjectsUtil.getProjectsList(sketchwareFolder)


    /**
     * Checks next free id and returns it.
     * @param startId - Specific id from which function
     * will check next free id. By default it is 601.
     * @return next free for new project id.
     */
    override suspend fun nextFreeId(startId: Int) =
        SketchwareProjectsUtil.nextFreeProjectId(File(sketchwareFolder, "mysc/list"))

    /**
     * @return instance of [ProjectImportManager] to import project.
     */
    fun getImporter(exportedFolder: File, newId: Int) = ProjectImportManager(
        ProjectFilesLocations.getDefaultExport(exportedFolder),
        ProjectFilesLocations.getSWDefault(sketchwareFolder, newId),
        newId
    )

}