package io.sketchware.models.projects

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Stores data about moreblock (name and data (spec)).
 */
@Serializable
data class SketchwareMoreblockModel(
    /**
     * Moreblock unique name.
     */
    val name: String,
    /**
     * Moreblock spec.
     */
    @Contextual
    val data: List<SpecField>
) {
    /**
     * Converts to sketchware value format.
     */
    override fun toString(): String {
        return "$name:$data"
    }
}
