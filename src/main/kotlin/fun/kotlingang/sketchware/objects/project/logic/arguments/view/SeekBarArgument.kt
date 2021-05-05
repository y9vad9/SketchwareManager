package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains SeekBarArgument id.
 * @param viewId - SeekBarArgument id.
 */
open class SeekBarArgument(override var viewId: String? = null) : ViewArgument(viewId)