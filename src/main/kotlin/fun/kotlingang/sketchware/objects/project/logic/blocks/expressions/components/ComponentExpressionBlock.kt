package `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components

import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.GetVariableBlock

sealed class ComponentExpressionBlock(model: BlockModel, arguments: List<Argument> = emptyList()) :
    GetVariableBlock(model, arguments)