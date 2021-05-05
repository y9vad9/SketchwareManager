package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.FilePickerExpressionBlock

class FilePickerComponentPickerMenuArgument(
    componentName: String? = null,
    expressionSource: FilePickerExpressionBlock?
) : ComponentPickerMenuArgument<FilePickerExpressionBlock>(componentName.nullIfBlank(), expressionSource)