package io.sketchware.models.export

import io.sketchware.models.ProjectType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ExportConfig(
    val version: Double,
    @Contextual
    val projectType: ProjectType,
    val filesConfig: ExportFilesConfig,
    val customFilesConfig: ExportedCustomFilesConfig? = null
)