package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.VibratorExpressionBlock

class VibratorComponentPickerMenuArgument(componentName: String? = null, expressionSource: VibratorExpressionBlock?) :
    ComponentPickerMenuArgument<VibratorExpressionBlock>(componentName.nullIfBlank(), expressionSource)