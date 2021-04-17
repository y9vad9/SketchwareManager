package io.sketchware.model.custom

import kotlinx.serialization.Serializable

@Serializable
data class Palette(
    val color: String,
    val name: String
)