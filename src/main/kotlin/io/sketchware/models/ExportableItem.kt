package io.sketchware.models

import java.io.File

data class ExportableItem(
    val internalPath: String,
    val file: File
)

