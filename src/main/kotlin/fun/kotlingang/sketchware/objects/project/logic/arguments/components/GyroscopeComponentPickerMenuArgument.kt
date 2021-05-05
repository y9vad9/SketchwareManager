package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.GyroscopeExpressionBlock

class GyroscopeComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: GyroscopeExpressionBlock?
) : ComponentPickerMenuArgument<GyroscopeExpressionBlock>(componentName.nullIfBlank(), expressionSource)