package io.sketchware.interfaces

fun interface ComponentIdProvider {
    fun provide(conflictId: Int): Int
}