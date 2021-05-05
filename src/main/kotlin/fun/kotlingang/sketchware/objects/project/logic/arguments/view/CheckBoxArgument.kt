package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains CheckBoxArgument id.
 * @param viewId - CheckBoxArgument id.
 */
open class CheckBoxArgument(override var viewId: String? = null) : ViewArgument(viewId)