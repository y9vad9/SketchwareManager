package `fun`.kotlingang.sketchware.objects.customs

import `fun`.kotlingang.sketchware.internal.json.serializers.SpecSerializer
import `fun`.kotlingang.sketchware.objects.project.logic.SpecField
import kotlinx.serialization.Serializable

/**
 * Sketchware mods custom event model.
 * Stores data about custom event,
 * in Custom Listeners SketchwareManager common model for Sketchware Pro and Studio.
 */
@Serializable
data class CustomEvent(
    /**
     * Spec of listener
     */
    @Serializable(with = SpecSerializer::class)
    var spec: List<SpecField>,
    /**
     * Id of icons which displaying for every listener.
     */
    var iconId: Int,
    /**
     * Unique name of event.
     */
    var id: String,
    /**
     * Description of the current event..
     */
    var description: String,
    /**
     * Event parameters.
     */
    var parameters: String,
    /**
     * Event name.
     */
    var name: String,
    /**
     * Event source code.
     */
    var code: String
)