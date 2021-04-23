package `fun`.kotlingang.sketchware.objects.project.logic

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class VariableModel(
    /**
     * Variable type id.
     */
    @Contextual
    val type: VariableType,
    /**
     * Name of variable.
     */
    val name: String
) {
    /**
     * Converts the object to the original Sketchware look.
     */
    override fun toString() = "${type.id}:$name"
}
