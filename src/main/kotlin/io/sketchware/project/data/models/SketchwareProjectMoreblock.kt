package io.sketchware.project.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SketchwareProjectMoreblock(
    val name: String,
    val data: String
) {
    override fun toString(): String {
        return "$name:$data"
    }
}