package io.sketchware.utils

import io.sketchware.manager.projects.entities.SketchwareProject
import io.sketchware.models.projects.ProjectFilesLocations

open class ProjectImportManager(
    open val locations: ProjectFilesLocations,
    open val destination: ProjectFilesLocations,
    open val projectId: Int
) {
    open suspend fun import() =
        SketchwareProject(locations).copy(projectId, destination)
}