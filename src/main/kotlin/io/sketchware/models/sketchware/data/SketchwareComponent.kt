package io.sketchware.models.sketchware.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SketchwareComponent(
    @SerialName("componentId")
    val id: String,
    val param1: String,
    val param2: String,
    val param3: String,
    val type: Int
)
