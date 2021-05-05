package `fun`.kotlingang.sketchware.objects.project.logic.arguments.structured

import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured.ListStringExpressionBlock

class ListStringExpressibleArgument(variableNameSource: String?, expressionSource: ListStringExpressionBlock?) :
    ListExpressibleArgument<ListStringExpressionBlock>(
        variableNameSource, expressionSource
    )