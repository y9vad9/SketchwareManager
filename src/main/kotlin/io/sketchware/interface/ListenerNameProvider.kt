package io.sketchware.`interface`

fun interface ListenerNameProvider {
    fun provide(conflictName: String): String
}