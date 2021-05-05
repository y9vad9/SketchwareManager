package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.MediaPlayerExpressionBlock

class MediaPlayerComponentPicker(componentName: String? = null, expressionSource: MediaPlayerExpressionBlock?) :
    ComponentPickerMenuArgument<MediaPlayerExpressionBlock>(componentName.nullIfBlank(), expressionSource)