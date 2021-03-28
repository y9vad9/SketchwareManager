package io.sketchware.interfaces

fun interface ListenerNameProvider {
    fun provide(conflictName: String): String
}