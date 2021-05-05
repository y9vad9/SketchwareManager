package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.LocationManagerExpressionBlock

class LocationManagerComponentPicker(
    componentName: String? = null,
    expressionSource: LocationManagerExpressionBlock?
) : ComponentPickerMenuArgument<LocationManagerExpressionBlock>(componentName.nullIfBlank(), expressionSource)