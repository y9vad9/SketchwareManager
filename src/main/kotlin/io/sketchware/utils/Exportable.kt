package io.sketchware.utils

import io.sketchware.models.ExportableItem
import java.io.File

class Exportable {
}

fun MutableList<ExportableItem>.add(internalPath: String, file: File) =
    add(ExportableItem(internalPath, file))