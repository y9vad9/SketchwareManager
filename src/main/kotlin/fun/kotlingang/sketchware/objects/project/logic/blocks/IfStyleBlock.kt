package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives.BooleanExpressibleArgument

open class IfStyleBlock(
    model: BlockModel, private val subStack1: MutableList<BaseBlock>,
    arguments: List<Argument>
) : BaseBlock(model, arguments) {
    /**
     * Gets blocks in first sub stack.
     * @return [MutableList] of [BaseBlock] with blocks in first sub stack.
     */
    val firstSubStack: MutableList<BaseBlock> get() = subStack1


    /**
     * @return casted first argument to [BooleanExpressibleArgument].
     */
    val condition get() = arguments[0] as BooleanExpressibleArgument

}