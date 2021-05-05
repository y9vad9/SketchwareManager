package `fun`.kotlingang.sketchware.objects.project.logic.arguments.view

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument

/**
 * Argument in blocks which contains ImageViewArgument id.
 * @param viewId - ImageViewArgument id.
 */
class ImageViewArgument(override var viewId: String? = null) : ViewArgument(viewId)