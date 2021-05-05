package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

import `fun`.kotlingang.sketchware.interfaces.objects.ExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.primitives.BooleanExpressionBlock

class BooleanExpressibleArgument(
    private var inputSource: Boolean?,
    private var expressionSource: BooleanExpressionBlock?
) : BooleanInputOnlyArgument(inputSource), ExpressibleArgument<BooleanExpressionBlock> {

    override var input: Boolean?
        get() = inputSource
        set(value) {
            expressionSource = null
            inputSource = value
        }

    /**
     * Block which returning [Boolean] in application runtime.
     */
    override var expression: BooleanExpressionBlock?
        get() = expressionSource
        set(value) {
            inputSource = null
            expressionSource = value
        }

}