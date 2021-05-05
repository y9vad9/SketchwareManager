package `fun`.kotlingang.sketchware.interfaces.objects

import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.ExpressionBlock

/**
 * Used for arguments where a block can be invoked,
 * which returns an appropriate value.
 */
interface ExpressibleArgument<EB : ExpressionBlock> {
    /**
     * Block which returning some appropriate value.
     */
    var expression: EB?
}