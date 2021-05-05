package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.SharedPreferencesExpressionBlock

class SharedPreferencesComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: SharedPreferencesExpressionBlock?
) : ComponentPickerMenuArgument<SharedPreferencesExpressionBlock>(componentName.nullIfBlank(), expressionSource)