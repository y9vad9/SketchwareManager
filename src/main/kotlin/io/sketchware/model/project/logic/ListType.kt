package io.sketchware.model.project.logic

import io.sketchware.`interface`.IdInterface

enum class ListType(override val id: Int, val serialName: String): IdInterface {
    LIST_NUMBER(1, "listInt"),
    LIST_STRING(2, "listStr"),
    LIST_MAP(3, "listMap")
}