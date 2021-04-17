package io.sketchware.model.view

import io.sketchware.`interface`.IdInterface

enum class LayoutOrientation(override val id: Int) : IdInterface {
    VERTICAL(1), HORIZONTAL(-1)
}