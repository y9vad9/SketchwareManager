package io.sketchware.util.delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal fun <T> lazyResetable(initFunction: () -> T) = LazyResetable(initFunction)

internal class LazyResetable<T>(private val initFunction: () -> T) : ReadOnlyProperty<Any?, T> {

    private var valueSource: T? = null

    fun reset() = synchronized(this) {
        valueSource = null
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = synchronized(this) {
        valueSource ?: initFunction().also { valueSource = it }
    }

}