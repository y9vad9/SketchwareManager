package `fun`.kotlingang.sketchware.internal.extensions

internal inline fun <reified T : Enum<*>> enumValueOf(value: String) =
    enumValues<T>().firstOrNull { it.name == value }