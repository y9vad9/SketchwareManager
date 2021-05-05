package `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.textview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TextStyle {
    NORMAL,
    BOLD,
    ITALIC,

    @SerialName("bold|italic")
    BOLD_AND_ITALIC;

    override fun toString() = name.toLowerCase()

}