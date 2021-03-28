package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface

enum class FileType(override val id: Int) : IdInterface {
    Activity(0), CustomView(1)
}