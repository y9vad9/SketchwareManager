package `fun`.kotlingang.sketchware.objects.project.resources

import kotlinx.serialization.Contextual
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectResource(
    /**
     * Full name of resource (example: logo.png)
     */
    @SerialName("resFullName")
    val fullName: String,
    /**
     * Resource name (example: logo)
     */
    @SerialName("resName")
    val name: String,
    /**
     * A presumably useless field as it always has a [ResourceType.DEFAULT].
     */
    @SerialName("resType")
    @Contextual
    @Required
    val type: ResourceType = ResourceType.DEFAULT
)