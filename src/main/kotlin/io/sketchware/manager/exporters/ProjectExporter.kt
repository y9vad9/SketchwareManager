package io.sketchware.manager.exporters

import io.sketchware.models.ProjectType
import io.sketchware.models.export.ExportConfig
import io.sketchware.models.export.ExportFilesConfig
import io.sketchware.models.export.ExportedCustomFilesConfig
import io.sketchware.models.projects.ProjectFilesLocations
import io.sketchware.models.projects.ProjectResourcesFiles
import io.sketchware.utils.internal.copy
import io.sketchware.utils.internal.deserialize
import io.sketchware.utils.internal.write
import java.io.File

open class ProjectExporter(
    open val locations: ProjectFilesLocations,
    open val projectType: ProjectType = ProjectType.SKETCHWARE
) {
    companion object {
        /**
         * Const with version of project exporter.
         */
        const val PROJECT_EXPORTER_VERSION = 0.1
    }

    /**
     * Exports project into [destinationFolder].
     */
    open suspend fun exportAsFolder(destinationFolder: File) = exportAsFolder(
        destinationFolder, File(destinationFolder, "config"), getDefaultDestLocations(destinationFolder)
    )

    /**
     * Exports project into a folder by [destLocations] paths.
     */
    open suspend fun exportAsFolder(
        rootFolder: File,
        configFile: File = File(rootFolder, "config"),
        destLocations: ProjectFilesLocations
    ) = with(locations) {
        dataFolder.copy(destLocations.dataFolder)
        projectFile.copy(destLocations.projectFile)
        resources.soundsFolder.copy(destLocations.resources.soundsFolder)
        resources.fontsFolder.copy(destLocations.resources.fontsFolder)
        resources.iconsFolder.copy(destLocations.resources.iconsFolder)
        resources.imagesFolder.copy(destLocations.resources.iconsFolder)
        configFile.write(getDefaultConfig().deserialize().toByteArray())
    }

    private fun getDefaultDestLocations(root: File) = ProjectFilesLocations(
        root, File(root, "project"), File(root, "data"), ProjectResourcesFiles(
            File(root, "resources/icons"), File(root, "resources/images"),
            File(root, "resources/sounds"), File(root, "resources/fonts")
        )
    )

    private fun getDefaultConfig() = ExportConfig(PROJECT_EXPORTER_VERSION, projectType, ExportFilesConfig())
}