package `fun`.kotlingang.sketchware.objects.project.logic

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ListVariableModel(
    var name: String,
    @Contextual
    var type: ListType
) {
    override fun toString(): String {
        return "${type.id}:$name"
    }
}