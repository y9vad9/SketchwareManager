package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains CalendarViewArgument id.
 * @param viewId - CalendarViewArgument id.
 */
open class CalendarViewArgument(override var viewId: String? = null) : ViewArgument(viewId)