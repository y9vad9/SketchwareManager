package io.sketchware.model.project.logic

import io.sketchware.`interface`.IdInterface

enum class VariableType(override val id: Int = -1, val serialName: String? = null) : IdInterface {
    BOOLEAN(0),
    NUMBER(1),
    STRING(2),
    MAP(3, "varMap")
}