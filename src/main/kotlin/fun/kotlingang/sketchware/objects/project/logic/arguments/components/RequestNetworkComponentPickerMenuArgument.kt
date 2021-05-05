package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.RequestNetworkExpressionBlock

class RequestNetworkComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: RequestNetworkExpressionBlock?
) : ComponentPickerMenuArgument<RequestNetworkExpressionBlock>(componentName.nullIfBlank(), expressionSource)