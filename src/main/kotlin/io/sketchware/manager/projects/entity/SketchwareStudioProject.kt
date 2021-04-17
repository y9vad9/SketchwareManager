package io.sketchware.manager.projects.entity

import io.sketchware.model.project.ProjectFilesLocations
import java.io.File

class SketchwareStudioProject(
    override val locations: ProjectFilesLocations,
    override val sourceEditedFolder: File,
) : SketchwareProProject(locations, sourceEditedFolder)