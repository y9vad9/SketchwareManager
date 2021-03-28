package io.sketchware.utils.internal

import io.sketchware.manager.projects.entities.SketchwareProject
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.models.projects.ProjectResourcesFiles
import java.io.File

internal object SketchwareProjectsUtil {

    suspend fun getProjectsList(baseFolder: File) = File(baseFolder, "mysc/list").getFiles().map {
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
        if (listFolder.getFiles().any { it.name == startId.toString() })
            nextFreeProjectId(listFolder, startId + 1)
        else startId

}