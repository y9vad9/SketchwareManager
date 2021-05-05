package `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument

sealed class ListExpressionBlock(
    model: BlockModel, arguments: List<Argument> = emptyList()
) : StructuredExpressionBlock(model, arguments)