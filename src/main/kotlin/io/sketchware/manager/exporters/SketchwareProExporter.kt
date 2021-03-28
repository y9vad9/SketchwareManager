package io.sketchware.manager.exporters

import io.sketchware.manager.customs.SketchwareProCustomManager
import io.sketchware.manager.projects.entities.SketchwareProProject
import io.sketchware.models.ProjectType
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.utils.internal.ProjectCustomExporter
import io.sketchware.utils.internal.deserialize
import io.sketchware.utils.internal.write
import java.io.File

class SketchwareProExporter(
    override val locations: ProjectFilesLocations,
    override val projectType: ProjectType = ProjectType.SKETCHWARE_PRO,
    val project: SketchwareProProject,
    val customsManager: SketchwareProCustomManager
) : ProjectExporter(locations, projectType) {
    private val customExporter by lazy { ProjectCustomExporter(project, customsManager) }

    override suspend fun exportAsFolder(rootFolder: File, configFile: File, destLocations: ProjectFilesLocations) {
        super.exportAsFolder(rootFolder, configFile, destLocations)
        File(rootFolder, "/customs").apply {
            mkdir()
            File(this, "blocks.json")
                .write(customExporter.getBlocksToExport().deserialize().toByteArray())
            File(this, "components.json")
                .write(customExporter.getComponentsToExport().deserialize().toByteArray())
            File(this, "menus.json")
                .write(customExporter.getMenusToExport().deserialize().toByteArray())
            File(this, "listeners.json")
                .write(customExporter.getListenersGroupsToExport().deserialize().toByteArray())
        }
    }

}