package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains MapViewArgument id.
 * @param viewId - MapViewArgument id.
 */
class MapViewArgument(override var viewId: String? = null) : ViewArgument(viewId)