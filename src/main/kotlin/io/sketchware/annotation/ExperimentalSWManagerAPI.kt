package io.sketchware.annotation

/**
 * Prepares that the original API can be changed at any time
 * or is not stable in operation (for example, if the functionality
 * of a particular moment has not been developed or tested to the end).
 * Be careful with this.
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION, AnnotationTarget.TYPEALIAS)
@RequiresOptIn(level = RequiresOptIn.Level.WARNING)
annotation class ExperimentalSWManagerAPI
