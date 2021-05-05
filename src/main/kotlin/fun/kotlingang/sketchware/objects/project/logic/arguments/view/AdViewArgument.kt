package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains AdViewArgument id.
 * @param viewId - AdViewArgument id.
 */
open class AdViewArgument(override var viewId: String? = null) : ViewArgument(viewId)