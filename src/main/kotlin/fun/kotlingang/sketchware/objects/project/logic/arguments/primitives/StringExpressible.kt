package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

import `fun`.kotlingang.sketchware.interfaces.objects.ExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.primitives.StringExpressionBlock

/**
 * Argument for blocks where, in addition to the string argument,
 * can be a block that returns the same string type.
 */
open class StringExpressible(
    private var inputStringSource: String? = "",
    private var expressionSource: StringExpressionBlock? = null
) : StringInputOnly(inputStringSource), ExpressibleArgument<StringExpressionBlock> {

    override var input: String?
        /**
         * @return [String] with value in argument.
         */
        get() = super.input
        /**
         * Set string to argument and removes [expression] data if it set.
         */
        set(value) {
            inputStringSource = value
            expression = null
        }


    override var expression: StringExpressionBlock?
        /**
         * @return [StringExpressionBlock] with block data.
         */
        get() = expressionSource
        /**
         * Set block to argument and removes [input] if it set.
         */
        set(value) {
            expressionSource = value
            inputStringSource = null
        }
}