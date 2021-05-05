package `fun`.kotlingang.sketchware.objects.project.view.properties

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable

enum class TextStyle(override val id: Int) : Identifiable {
    NORMAL(0), BOLD(1), ITALIC(2), BOLD_AND_ITALIC(3)
}