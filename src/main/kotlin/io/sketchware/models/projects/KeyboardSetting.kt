package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface

enum class KeyboardSetting(override val id: Int) : IdInterface {
    Unspecified(0), Visible(1), Hidden(2)
}