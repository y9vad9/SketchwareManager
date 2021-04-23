package `fun`.kotlingang.sketchware.objects.project.logic

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Stores data about a component in a project.
 */
@Serializable
data class ComponentModel(
    /**
     * The unique name of the component in the activity.
     */
    @SerialName("componentId")
    val id: String,
    /**
     * Any data about a component (may be missing) is different everywhere.
     */
    val param1: String,
    /**
     * Any data about a component (may be missing) is different everywhere.
     */
    val param2: String,
    /**
     * Any data about a component (may be missing) is different everywhere.
     */
    val param3: String,
    /**
     * Unique component type id.
     */
    val type: Int
)