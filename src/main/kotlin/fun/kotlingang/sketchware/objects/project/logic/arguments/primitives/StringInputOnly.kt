package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

import `fun`.kotlingang.sketchware.interfaces.objects.InputOnly
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.PrimitiveArgument

/**
 * Argument for blocks that can only take a string as an argument, such as ASD.
 * @param input - [String] with value in argument.
 */
open class StringInputOnly(override var input: String? = "") : PrimitiveArgument(), InputOnly<String>