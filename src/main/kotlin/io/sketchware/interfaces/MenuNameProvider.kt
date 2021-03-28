package io.sketchware.interfaces

interface MenuNameProvider {
    fun provide(conflictName: String): String
}