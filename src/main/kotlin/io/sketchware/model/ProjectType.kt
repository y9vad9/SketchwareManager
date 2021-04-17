package io.sketchware.model

import io.sketchware.`interface`.IdInterface

enum class ProjectType(override val id: Int) : IdInterface {
    SKETCHWARE(1), SKETCHWARE_PRO(2), SKETCHWARE_STUDIO(3)
}