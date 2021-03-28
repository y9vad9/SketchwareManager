package io.sketchware.models.projects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stores event data.
 */
@Serializable
data class SketchwareEventModel(
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
    var targetType: Int
)