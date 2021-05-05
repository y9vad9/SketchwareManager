package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

/**
 * Argument for blocks where, in addition to the number argument,
 * can be a block that returns number.
 */
open class NumberExpressible(inputNumber: Double? = null) : NumberInputOnly(inputNumber)