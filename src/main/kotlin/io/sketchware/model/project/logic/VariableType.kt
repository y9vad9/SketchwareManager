package io.sketchware.model.project.logic

enum class VariableType(val id: Int = -1, val serialName: String? = null) {
    BOOLEAN,
    NUMBER,
    STRING,
    MAP(0, "varMap"),
    LIST_NUMBER(0, "listInt"),
    LIST_STRING(0, "listStr"),
    LIST_MAP(0, "listMap")
}