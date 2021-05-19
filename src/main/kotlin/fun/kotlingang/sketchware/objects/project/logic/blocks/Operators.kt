package `fun`.kotlingang.sketchware.objects.project.logic.blocks

sealed interface OperatorBlock : Block

sealed interface IfStyledBlock : OperatorBlock {
    /**
     * First sub stack with list of blocks inside.
     * @return [List] with [Block] items.
     */
    val firstSubStack: MutableList<Block>
}

sealed interface IfElseStyledBlock : IfStyledBlock {
    /**
     * Second sub stack with list of blocks inside.
     * @return [List] with [Block] items.
     */
    val secondSubStack: MutableList<Block>
}

/**
 * Block after which you can't place blocks.
 * For example, finish activity / affinity blocks.
 */
sealed interface StopStyledBlock : OperatorBlock