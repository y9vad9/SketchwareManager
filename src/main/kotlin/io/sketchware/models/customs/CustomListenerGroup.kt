package io.sketchware.models.customs

import kotlinx.serialization.Serializable


@Serializable
data class CustomListenerGroup(
    /**
     * Event name.
     */
    var name: String,
    /**
     * Independent class/method.
     */
    var independent: Boolean,
    /**
     * Custom import for event.
     */
    var customImport: String,
    /**
     * Code of event group.
     */
    var code: String,
    var events: List<CustomEvent>
)