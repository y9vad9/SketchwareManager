package io.sketchware.model.project.content

import io.sketchware.`interface`.IdInterface
import io.sketchware.util.SWConst

enum class Orientation(override val id: Int) : IdInterface {
    PORTRAIT(SWConst.ThemeOrientation.ORIENTATION_PORTRAIT),
    LANDSCAPE(SWConst.ThemeOrientation.ORIENTATION_LANDSCAPE),
    BOTH(SWConst.ThemeOrientation.ORIENTATION_BOTH)
}