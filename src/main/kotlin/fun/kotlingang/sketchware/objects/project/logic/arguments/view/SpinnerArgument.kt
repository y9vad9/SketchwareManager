package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains SpinnerArgument id.
 * @param viewId - SpinnerArgument id.
 */
open class SpinnerArgument(override var viewId: String? = null) : ViewArgument(viewId)