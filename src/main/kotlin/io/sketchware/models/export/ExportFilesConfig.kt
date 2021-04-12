package io.sketchware.models.export

import kotlinx.serialization.Serializable

@Serializable
data class ExportFilesConfig(
    val projectFileName: String = "project",
    val dataFolderName: String = "data",
    val resources: ExportDataFilesConfig = ExportDataFilesConfig()
) {
    companion object {
        /**
         * @return Default config which are uses for SH-Recovery projects backups.
         */
        fun getSHRecoveryDefaultConfig() = ExportFilesConfig(
            "project", "data",
            ExportDataFilesConfig(
                "icons", "images", "fonts", "sounds"
            )
        )
    }
}

@Serializable
data class ExportDataFilesConfig(
    val iconsFolderName: String = "resources/icons",
    val imagesFolderName: String = "resources/images",
    val fontsFolderName: String = "resources/fonts",
    val soundsFolderName: String = "resources/sounds"
)