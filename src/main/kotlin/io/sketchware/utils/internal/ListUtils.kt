package io.sketchware.utils.internal

internal fun List<Int>.freeBetweenOrNull(first: Int, second: Int): Int? {
    (first..second).toList().forEach { number ->
        if(!contains(number))
            return number
    }
    return null
}

internal fun <T> List<T>.freeBetweenOrNull(first: Int, max: Int, transformer: (T) -> Int) =
    map(transformer).freeBetweenOrNull(first, max)

internal fun <T> List<T>.freeBetweenOrDefault(first: Int, max: Int, default: Int, transformer: (T) -> Int) =
    map(transformer).freeBetweenOrNull(first, max) ?: default