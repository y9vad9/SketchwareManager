package io.sketchware.`interface`

fun interface ComponentIdProvider {
    fun provide(conflictId: Int): Int
}