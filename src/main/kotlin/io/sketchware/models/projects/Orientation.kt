package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface

enum class Orientation(override val id: Int) : IdInterface {
    Portrait(0), Landscape(1), Both(2)
}