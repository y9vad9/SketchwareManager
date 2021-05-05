package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument

open class BaseBlock(internal val model: BlockModel, val arguments: List<Argument>) {
    /**
     * Block color as [Int] value.
     */
    val color by model::color

    /**
     * Block spec.
     */
    val spec by model::spec
}