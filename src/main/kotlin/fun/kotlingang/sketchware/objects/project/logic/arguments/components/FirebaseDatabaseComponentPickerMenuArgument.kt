package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.FirebaseDatabaseExpressionBlock

class FirebaseDatabaseComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: FirebaseDatabaseExpressionBlock?
) : ComponentPickerMenuArgument<FirebaseDatabaseExpressionBlock>(componentName.nullIfBlank(), expressionSource)