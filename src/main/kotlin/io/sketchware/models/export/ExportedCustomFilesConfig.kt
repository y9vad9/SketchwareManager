package io.sketchware.models.export

import kotlinx.serialization.Serializable
import java.io.File

@Serializable
data class ExportedCustomFilesConfig(
    val blocksFileName: String = "customs/blocks.json",
    val componentsFileName: String = "customs/components.json",
    val listenersFileName: String = "custom/listeners.json",
    val menusFileName: String = "custom/menus.json"
) {
    fun toExportedCustomFiles(rootFolder: File) =
        ExportedCustomFiles(
            File(rootFolder, blocksFileName),
            File(rootFolder, componentsFileName),
            File(rootFolder, listenersFileName),
            File(rootFolder, menusFileName)
        )
}

data class ExportedCustomFiles(
    val blocksFile: File,
    val componentsFile: File,
    val listenersFile: File,
    val menusFile: File
)
