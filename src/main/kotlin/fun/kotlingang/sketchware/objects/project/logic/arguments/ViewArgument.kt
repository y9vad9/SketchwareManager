package `fun`.kotlingang.sketchware.objects.project.logic.arguments

/**
 * Argument in blocks which contains view id.
 * @param viewId - view unique id.
 */
open class ViewArgument internal constructor(open var viewId: String? = null) : Argument()