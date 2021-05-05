package `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.GetVariableBlock

sealed class StructuredExpressionBlock(model: BlockModel, arguments: List<Argument>) : GetVariableBlock(
    model,
    arguments
)