package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.CalendarExpressionBlock

class CalendarComponentPickerMenuArgument(componentName: String? = null, expressionSource: CalendarExpressionBlock?) :
    ComponentPickerMenuArgument<CalendarExpressionBlock>(componentName.nullIfBlank(), expressionSource)