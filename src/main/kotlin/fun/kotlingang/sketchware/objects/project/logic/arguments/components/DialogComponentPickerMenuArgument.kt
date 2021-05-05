package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.DialogExpressionBlock

class DialogComponentPickerMenuArgument(componentName: String? = null, expressionSource: DialogExpressionBlock?) :
    ComponentPickerMenuArgument<DialogExpressionBlock>(componentName.nullIfBlank(), expressionSource)