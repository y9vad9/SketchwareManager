package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains ListViewArgument id.
 * @param viewId - ListViewArgument id.
 */
class ListViewArgument(override var viewId: String? = null) : ViewArgument(viewId)