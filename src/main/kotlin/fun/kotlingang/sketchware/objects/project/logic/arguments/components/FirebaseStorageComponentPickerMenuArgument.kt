package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.FirebaseStorageExpressionBlock

class FirebaseStorageComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: FirebaseStorageExpressionBlock?
) : ComponentPickerMenuArgument<FirebaseStorageExpressionBlock>(componentName.nullIfBlank(), expressionSource)