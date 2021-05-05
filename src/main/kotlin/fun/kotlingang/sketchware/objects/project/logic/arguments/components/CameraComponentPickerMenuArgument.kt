package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.CameraExpressionBlock

class CameraComponentPickerMenuArgument(componentName: String? = null, expressionSource: CameraExpressionBlock?) :
    ComponentPickerMenuArgument<CameraExpressionBlock>(componentName.nullIfBlank(), expressionSource)