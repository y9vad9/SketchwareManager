package io.sketchware.model.project.view

import io.sketchware.`interface`.IdInterface

enum class SpinnerMode(override val id: Int) : IdInterface {
    DROPDOWN(1), DIALOG(0)
}
