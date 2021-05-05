package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetType
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stores event data.
 */
@Serializable
data class EventModel(
    /**
     * Event name (example: onClick).
     */
    @SerialName("eventName")
    var name: String,
    /**
     * Unique event type id.
     */
    @SerialName("eventType")
    var type: Int,
    /**
     * Event target id (example: button1).
     */
    var targetId: String,
    /**
     * Event target type id.
     */
    @Contextual
    var targetType: WidgetType
)