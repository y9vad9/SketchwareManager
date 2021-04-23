package `fun`.kotlingang.sketchware.objects.project.view.properties

import `fun`.kotlingang.sketchware.interfaces.Identifiable

enum class SpinnerMode(override val id: Int) : Identifiable {
    DROPDOWN(1), DIALOG(0)
}
