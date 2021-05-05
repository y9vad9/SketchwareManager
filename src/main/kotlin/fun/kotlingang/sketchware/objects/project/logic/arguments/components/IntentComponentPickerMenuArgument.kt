package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.IntentExpressionBlock

class IntentComponentPickerMenuArgument(componentName: String? = null, expressionSource: IntentExpressionBlock?) :
    ComponentPickerMenuArgument<IntentExpressionBlock>(componentName.nullIfBlank(), expressionSource)