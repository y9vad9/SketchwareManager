package `fun`.kotlingang.sketchware.objects.customs

import kotlinx.serialization.Serializable

@Serializable
data class Palette(
    val color: String,
    val name: String
)