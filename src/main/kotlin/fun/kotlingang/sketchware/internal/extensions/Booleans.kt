package `fun`.kotlingang.sketchware.internal.extensions

fun String.toBooleanOrNull(): Boolean? {
    return when {
        toLowerCase().trim() == "true" -> true
        toLowerCase().trim() == "false" -> false
        else -> null
    }
}