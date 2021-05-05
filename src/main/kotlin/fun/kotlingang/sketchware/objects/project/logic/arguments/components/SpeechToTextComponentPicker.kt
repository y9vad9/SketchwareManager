package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.SpeechToTextExpressionBlock

class SpeechToTextComponentPicker(
    componentName: String? = null,
    expressionSource: SpeechToTextExpressionBlock?
) : ComponentPickerMenuArgument<SpeechToTextExpressionBlock>(componentName.nullIfBlank(), expressionSource)