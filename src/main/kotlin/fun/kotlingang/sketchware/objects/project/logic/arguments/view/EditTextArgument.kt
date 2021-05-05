package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

/**
 * Argument in blocks which contains EditTextArgument id.
 * @param viewId - edittext id.
 */
sealed class EditTextArgument(override var viewId: String? = null) : TextViewArgument(viewId)