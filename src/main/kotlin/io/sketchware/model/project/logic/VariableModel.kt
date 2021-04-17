package io.sketchware.model.project.logic

data class VariableModel(
    /**
     * Variable type id.
     */
    val type: Int,
    /**
     * Name of variable.
     */
    val name: String
) {
    /**
     * Converts the object to the original Sketchware look.
     */
    override fun toString() = "$type:$name"
}
