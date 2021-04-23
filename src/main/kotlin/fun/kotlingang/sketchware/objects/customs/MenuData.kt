package `fun`.kotlingang.sketchware.objects.customs

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    val title: String,
    val value: String
)
