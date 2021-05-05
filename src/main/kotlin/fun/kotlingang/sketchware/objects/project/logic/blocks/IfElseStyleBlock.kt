package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives.BooleanExpressibleArgument

class IfElseStyleBlock(
    model: BlockModel,
    subStack1: MutableList<BaseBlock>,
    private val subStack2: MutableList<BaseBlock>, arguments: List<Argument>
) : IfStyleBlock(model, subStack1, arguments) {
    /**
     * Gets blocks in second sub stack.
     * @return [MutableList] of [BaseBlock] with blocks in second sub stack.
     */
    val secondSubStack get() = subStack2
}