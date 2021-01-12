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

}