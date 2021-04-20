package io.sketchware.model.project.view

import io.sketchware.`interface`.IdInterface

enum class TextStyle(override val id: Int) : IdInterface {
    NORMAL(0), BOLD(1), ITALIC(2), BOLD_AND_ITALIC(3)
}