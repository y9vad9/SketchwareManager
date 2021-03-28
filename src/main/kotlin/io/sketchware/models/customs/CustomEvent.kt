package io.sketchware.models.customs

import io.sketchware.models.projects.SpecField
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class CustomEvent(
    /**
     * Spec of listener
     */
    @Contextual
    var spec: List<SpecField>,
    /**
     * Id of icons which displaying for every listener.
     */
    var iconId: Int,
    /**
     * Unique name of event.
     */
    var id: String,
    var description: String,
    var parameters: String,
    var name: String,
    var code: String
)