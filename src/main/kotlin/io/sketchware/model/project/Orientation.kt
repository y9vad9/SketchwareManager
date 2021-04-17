package io.sketchware.model.project

import io.sketchware.`interface`.IdInterface
import io.sketchware.util.SWConst

enum class Orientation(override val id: Int) : IdInterface {
    Portrait(SWConst.ORIENTATION_PORTRAIT),
    Landscape(SWConst.ORIENTATION_LANDSCAPE),
    Both(SWConst.ORIENTATION_BOTH)
}