package io.sketchware.util.internal

internal fun <E> ObservableList(listener: (List<E>) -> Unit) =
    ObservableList(mutableListOf(), listener = listener)

class ObservableList<E> internal constructor(
    private val original: MutableList<E> = mutableListOf(),
    private val listener: (List<E>) -> Unit
) {

    val size get() = original.size

    operator fun get(index: Int) = original[index]

    fun add(element: E) = add(size, element)

    fun add(index: Int, element: E) {
        original.add(index, element)
        listener(original)
    }

    fun remove(element: E): Boolean {
        val status = original.remove(element)
        if (status)
            listener(original)
        return status
    }

    fun forEach(action: (E) -> Unit) = original.forEach(action)

}