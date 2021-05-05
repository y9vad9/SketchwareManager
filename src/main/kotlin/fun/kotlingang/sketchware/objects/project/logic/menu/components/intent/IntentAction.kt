package `fun`.kotlingang.sketchware.objects.project.logic.menu.components.intent

import kotlinx.serialization.Serializable

@Serializable
enum class IntentAction {
    ACTION_DIAL, ACTION_CALL, ACTION_VIEW;
}