package io.sketchware.utils.delegates

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal fun <T> lazyInit(initFunction: () -> T) = LazyInit(initFunction)

internal class LazyInit<T>(private val initFunction: () -> T) : ReadOnlyProperty<Any?, T> {

    private var valueSource: T? = null

    fun reset() = synchronized(this) {
        valueSource = null
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = synchronized(this) {
        valueSource ?: initFunction().also { valueSource = it }
    }

}