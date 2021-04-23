package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.interfaces.Identifiable

enum class VariableType(override val id: Int = -1, val serialName: String? = null) : Identifiable {
    BOOLEAN(0),
    NUMBER(1),
    STRING(2),
    MAP(3, "varMap")
}

internal fun variableTypeFrom(id: Int) = VariableType.values().find { it.id == id }
    ?: throw IllegalArgumentException("Couldn't find a suitable option for $id.")