package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.InterstitialAdExpressionBlock

class InterstitialAdComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: InterstitialAdExpressionBlock?
) : ComponentPickerMenuArgument<InterstitialAdExpressionBlock>(componentName.nullIfBlank(), expressionSource)