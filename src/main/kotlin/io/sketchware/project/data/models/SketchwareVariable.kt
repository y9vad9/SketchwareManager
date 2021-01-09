package io.sketchware.project.data.models

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