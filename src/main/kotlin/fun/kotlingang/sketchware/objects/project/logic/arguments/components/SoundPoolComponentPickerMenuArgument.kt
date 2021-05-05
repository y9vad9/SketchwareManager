package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.SoundPoolExpressionBlock


class SoundPoolComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: SoundPoolExpressionBlock?
) : ComponentPickerMenuArgument<SoundPoolExpressionBlock>(componentName.nullIfBlank(), expressionSource)