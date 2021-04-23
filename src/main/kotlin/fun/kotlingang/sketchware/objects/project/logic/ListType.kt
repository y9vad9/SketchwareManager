package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.interfaces.Identifiable

enum class ListType(override val id: Int, val serialName: String) : Identifiable {
    LIST_NUMBER(1, "listInt"),
    LIST_STRING(2, "listStr"),
    LIST_MAP(3, "listMap")
}