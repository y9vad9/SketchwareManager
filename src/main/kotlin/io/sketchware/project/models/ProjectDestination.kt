package io.sketchware.project.models

import java.io.File

data class ProjectDestination(
    val projectFile: File,
    val projectDataFolder: File,
    val projectResources: SketchwareProjectResources
) {
    companion object {
        fun from(sketchwarePath: String, projectId: Int) = fromFolder(File(sketchwarePath), projectId)

        fun fromFolder(folder: File, projectId: Int): ProjectDestination {
            val resourcesFolder = File(folder, "resources")
            return ProjectDestination(
                File(folder, "mysc/list/$projectId/project"),
                File(folder, "data/$projectId"),
                SketchwareProjectResources(
                    File(resourcesFolder, "images/$projectId"),
                    File(resourcesFolder, "icons/$projectId"),
                    File(resourcesFolder, "fonts/$projectId"),
                    File(resourcesFolder, "sounds/$projectId")
                )
            )
        }
    }
}