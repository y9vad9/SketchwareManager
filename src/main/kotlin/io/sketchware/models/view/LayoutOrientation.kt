package io.sketchware.models.view

import io.sketchware.interfaces.IdInterface

enum class LayoutOrientation(override val id: Int) : IdInterface {
    VERTICAL(1), HORIZONTAL(-1)
}