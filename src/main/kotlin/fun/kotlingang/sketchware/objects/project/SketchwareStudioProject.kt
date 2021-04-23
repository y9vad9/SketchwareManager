package `fun`.kotlingang.sketchware.objects.project

import `fun`.kotlingang.sketchware.objects.project.information.ProjectFilesLocations
import java.io.File

class SketchwareStudioProject(
    override val locations: ProjectFilesLocations,
    override val sourceEditedFolder: File,
) : SketchwareProProject(locations, sourceEditedFolder)