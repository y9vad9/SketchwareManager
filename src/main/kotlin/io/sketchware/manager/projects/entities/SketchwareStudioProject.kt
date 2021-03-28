package io.sketchware.manager.projects.entities

import io.sketchware.manager.customs.SketchwareStudioCustomManager
import io.sketchware.manager.exporters.SketchwareProExporter
import io.sketchware.models.ProjectType
import io.sketchware.models.projects.ProjectFilesLocations
import java.io.File

class SketchwareStudioProject(
    override val locations: ProjectFilesLocations,
    override val sourceEditedFolder: File,
) : SketchwareProProject(locations, sourceEditedFolder) {
    /**
     * Sketchware Studio Project exporter. Exports project fully with all custom components / etc.
     */
    fun getExporter(customManager: SketchwareStudioCustomManager): SketchwareProExporter {
        return SketchwareProExporter(locations, ProjectType.SKETCHWARE_STUDIO, this, customManager)
    }
}