package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.FirebaseAuthExpressionBlock

class FirebaseAuthComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: FirebaseAuthExpressionBlock?
) : ComponentPickerMenuArgument<FirebaseAuthExpressionBlock>(componentName.nullIfBlank(), expressionSource)