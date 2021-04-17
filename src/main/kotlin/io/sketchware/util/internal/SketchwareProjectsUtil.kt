package io.sketchware.util.internal

import io.sketchware.manager.projects.entities.SketchwareProject
import io.sketchware.model.project.ProjectFilesLocations
import io.sketchware.model.project.ProjectResourcesFiles
import java.io.File

internal object SketchwareProjectsUtil {

    suspend fun getProjectsList(baseFolder: File) = File(baseFolder, "mysc/list").getFilesOrEmpty().map {
        return@map SketchwareProject(
            ProjectFilesLocations(
                it, File(it, "project"), File(baseFolder, "data/${it.name}"),
                ProjectResourcesFiles(
                    File(baseFolder, "resources/icons/${it.name}"),
                    File(baseFolder, "resources/images/${it.name}"),
                    File(baseFolder, "resources/sounds/${it.name}"),
                    File(baseFolder, "resources/fonts/${it.name}")
                )
            )
        )
    }

    suspend fun nextFreeProjectId(listFolder: File, startId: Int = 601): Int =
        if (listFolder.getFilesOrEmpty().any { it.name == startId.toString() })
            nextFreeProjectId(listFolder, startId + 1)
        else startId

}