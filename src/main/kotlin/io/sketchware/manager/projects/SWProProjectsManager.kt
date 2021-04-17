package io.sketchware.manager.projects

import io.sketchware.`interface`.ProjectsManager
import io.sketchware.manager.projects.entity.SketchwareProProject
import io.sketchware.manager.projects.entity.SketchwareProject
import io.sketchware.manager.projects.entity.toSketchwareProProject
import io.sketchware.util.internal.SketchwareProjectsUtil
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
     * @return List of [SketchwareProject].
     */
    override suspend fun getProjects() = SketchwareProjectsUtil.getProjectsList(sketchwareFolder).map {
        it.toSketchwareProProject(
            File(sketchwareFolder, "data/SourceEdited/${it.locations.projectMainDirectory!!.name}")
        )
    }

    /**
     * Checks next free id and returns it.
     * @param startId - Specific id from which function
     * will check next free id. By default it is 601.
     * @return next free for new project id.
     */
    override suspend fun nextFreeId(startId: Int) =
        SketchwareProjectsUtil.nextFreeProjectId(File(sketchwareFolder, "mysc/list"))

}
