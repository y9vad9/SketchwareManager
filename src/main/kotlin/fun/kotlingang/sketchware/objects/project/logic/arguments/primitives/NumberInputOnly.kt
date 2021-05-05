package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

import `fun`.kotlingang.sketchware.interfaces.objects.InputOnly
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.PrimitiveArgument

/**
 * Argument for blocks that can only take a double as an argument.
 * @param input - [String] with value in argument.
 */
@Suppress("MemberVisibilityCanBePrivate")
sealed class NumberInputOnly(override var input: Double? = null) : PrimitiveArgument(), InputOnly<Double>