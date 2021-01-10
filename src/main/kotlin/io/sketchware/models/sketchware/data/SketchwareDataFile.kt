package io.sketchware.models.sketchware.data

import kotlinx.serialization.Serializable

@Serializable
class SketchwareDataFile(
    val fileName: String,
    val fileType: Int,
    val keyboardSettings: Int,
    val options: Int,
    val orientation: Int,
    val theme: Int
)