package io.sketchware.models.sketchware.data

import kotlinx.serialization.Serializable

@Serializable
data class SketchwareVariable(
    val name: String,
    val type: Int
) {
    override fun toString(): String {
        return "$type:$name"
    }
}