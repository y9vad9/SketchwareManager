package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.TextToSpeechExpressionBlock

class TextToSpeechComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: TextToSpeechExpressionBlock?
) : ComponentPickerMenuArgument<TextToSpeechExpressionBlock>(componentName.nullIfBlank(), expressionSource)