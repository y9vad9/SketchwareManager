package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains WebViewArgument id.
 * @param viewId - WebViewArgument id.
 */
open class WebViewArgument(override var viewId: String? = null) : ViewArgument(viewId)