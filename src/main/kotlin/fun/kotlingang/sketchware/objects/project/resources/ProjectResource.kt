package `fun`.kotlingang.sketchware.objects.project.resources

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
    @SerialName("resType")
    val type: Int
)