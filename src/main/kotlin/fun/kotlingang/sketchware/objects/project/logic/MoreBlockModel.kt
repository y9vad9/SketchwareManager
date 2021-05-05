package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.internal.json.serializers.SpecSerializer
import kotlinx.serialization.Serializable

/**
 * Stores data about moreblock (name and data (spec)).
 */
@Serializable
data class MoreBlockModel(
    /**
     * Moreblock unique name.
     */
    var name: String,
    /**
     * Moreblock spec.
     */
    @Serializable(with = SpecSerializer::class)
    var data: List<SpecField>
) {
    /**
     * Converts to sketchware value format.
     */
    override fun toString(): String {
        return "$name:${data.toStringValue()}"
    }
}
