package `fun`.kotlingang.sketchware.objects.project.logic

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ListVariableModel(
    val name: String,
    @Contextual
    val type: ListType
) {
    override fun toString(): String {
        return "${type.id}:$name"
    }
}