package io.sketchware.utils

import io.sketchware.manager.projects.entities.SketchwareProject
import io.sketchware.models.projects.ProjectFilesLocations

open class ProjectImportManager(
    internal open val locations: ProjectFilesLocations,
    internal open val destination: ProjectFilesLocations,
    internal open val projectId: Int
) {
    open suspend fun import() =
        SketchwareProject(locations).copy(projectId, destination)
}