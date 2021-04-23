package `fun`.kotlingang.sketchware.objects.project.view.properties

import `fun`.kotlingang.sketchware.interfaces.Identifiable

enum class LayoutOrientation(override val id: Int) : Identifiable {
    VERTICAL(1), HORIZONTAL(-1)
}