package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.objects.project.logic.SpecField
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument

/**
 * The root of any block.
 */
sealed interface Block {
    /**
     * Block's color as [Int] value.
     * @return [Int] with color of current block.
     */
    var color: Int

    val spec: List<BlockSpec>

    /**
     * Block's spec with data about block text, arguments, etc.
     * @return [List] with [SpecField] items inside.
     */
    val arguments: List<Argument>
}

/**
 * The root of any block that returns some value.
 */
sealed interface ReturnableBlock : Block
