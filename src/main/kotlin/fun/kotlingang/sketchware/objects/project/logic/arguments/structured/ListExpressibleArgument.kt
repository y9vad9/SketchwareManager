package `fun`.kotlingang.sketchware.objects.project.logic.arguments.structured

import `fun`.kotlingang.sketchware.interfaces.objects.CanBeAsVariableName
import `fun`.kotlingang.sketchware.interfaces.objects.ExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.StructuredArgument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured.ListExpressionBlock

sealed class ListExpressibleArgument<LT : ListExpressionBlock>(
    private var variableNameSource: String?,
    private var expressionSource: LT?
) : StructuredArgument(), ExpressibleArgument<LT>, CanBeAsVariableName {

    override var variableName: String?
        get() = variableNameSource
        set(value) {
            expressionSource = null
            variableNameSource = value
        }

    override var expression: LT?
        get() = expressionSource
        set(value) {
            variableNameSource = null
            expressionSource = value
        }
}