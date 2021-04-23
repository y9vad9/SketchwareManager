package `fun`.kotlingang.sketchware.objects.project.content

import `fun`.kotlingang.sketchware.interfaces.Identifiable
import `fun`.kotlingang.sketchware.objects.SWConst

enum class Orientation(override val id: Int) : Identifiable {
    PORTRAIT(SWConst.ThemeOrientation.ORIENTATION_PORTRAIT),
    LANDSCAPE(SWConst.ThemeOrientation.ORIENTATION_LANDSCAPE),
    BOTH(SWConst.ThemeOrientation.ORIENTATION_BOTH)
}