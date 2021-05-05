package `fun`.kotlingang.sketchware.objects.project.logic.menu.components.intent

import kotlinx.serialization.Serializable

@Serializable
enum class IntentFlag {
    SINGLE_TOP, CLEAR_TOP;
    companion object {
        fun of(name: String): IntentFlag? = values().firstOrNull { it.name == name }
    }
}