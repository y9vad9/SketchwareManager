package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

/**
 * Argument in blocks which contains SwitchArgument id.
 * @param viewId - SwitchArgument id.
 */
open class SwitchArgument(override var viewId: String? = null) : CheckBoxArgument(viewId)