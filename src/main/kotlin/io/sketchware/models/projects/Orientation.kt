package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface
import io.sketchware.utils.SWConst

enum class Orientation(override val id: Int) : IdInterface {
    Portrait(SWConst.ORIENTATION_PORTRAIT),
    Landscape(SWConst.ORIENTATION_LANDSCAPE),
    Both(SWConst.ORIENTATION_BOTH)
}