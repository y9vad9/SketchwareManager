package io.sketchware

import io.sketchware.models.exceptions.SketchwareFolderError
import io.sketchware.models.sketchware.ProjectFilesLocations
import io.sketchware.project.SketchwareProProject
import io.sketchware.project.SketchwareProject
import io.sketchware.utils.getListFiles
import java.io.File

class SketchwareProjects(private val sketchwareFolder: File) {

    init {
        if (sketchwareFolder.isFile)
            throw SketchwareFolderError(sketchwareFolder.path)
    }

    constructor(sketchwareFolder: String) : this(File(sketchwareFolder))

    suspend fun getProjects(): List<SketchwareProject>? {
        return File(sketchwareFolder, "mysc/list").getListFiles()?.map {
            if(isSketchwareProProject(it.name.toInt()))
                SketchwareProject(ProjectFilesLocations.defaultSketchwareProject(sketchwareFolder, it.name.toInt()))
            else SketchwareProProject(
                ProjectFilesLocations.defaultSketchwareProProject(sketchwareFolder, it.name.toInt())
            )
        }
    }

    val nextFreeId: Int get() = getFreeId(601)

    fun isSketchwareProProject(id: Int) =
        File(sketchwareFolder, "/data/$id/project_config").exists()

    private fun getFreeId(startId: Int): Int {
        File(File(sketchwareFolder, "mysc"), "list").listFiles()!!.forEach {
            if (it.name == startId.toString())
                return getFreeId(startId + 1)
        }
        return startId
    }

    /* Sketchware (and this library, ironically) gets a free ID by adding one to the highest
     * folder, making some ID's empty / wasted IDs if a project is deleted under the highest ID.
     *
     * Example scenario:
     * 601, 602, (603 deleted), (604 deleted), 605
     * Sketchware will create a project on 606, wasting 603, and 604
     *
     * This function will move some projects with higher IDs to the empty wasted IDs
     *
     * Example scenario:
     * 601, 602, (603 deleted), (604 deleted), 605
     *
     * After calling this function:
     * 601, 602, 603 (previously 605)
     *
     * This function might break some implementations that depends on the project ID, will add a
     * safer alternative soon
     *
     *  - Iyxan23
     */
    fun optimizeIds() {
        var indexProject = 601  // This variable is going to be the anchor
        var currentID = 601     // This variable is going to be the pointer that points to the current project id in this loop
        File(File(sketchwareFolder, "mysc"), "list").listFiles().forEach {
            currentID = it.name

            // TODO: IMPLEMENT THIS
        }
    }

}