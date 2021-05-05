package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.ComponentExpressionBlock

class BluetoothComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: ComponentExpressionBlock?
) : ComponentPickerMenuArgument<ComponentExpressionBlock>(componentName.nullIfBlank(), expressionSource)