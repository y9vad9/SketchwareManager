package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains textview id.
 * @param viewId - textview id.
 */
sealed class TextViewArgument(override var viewId: String? = null) : ViewArgument(viewId)