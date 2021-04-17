package io.sketchware.model.project.view

import io.sketchware.`interface`.IdInterface

enum class LayoutOrientation(override val id: Int) : IdInterface {
    VERTICAL(1), HORIZONTAL(-1)
}