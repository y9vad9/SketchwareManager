package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.EnumMenuArgument
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.view.WebViewCacheMode

/**
 * Argument in blocks which contains WebViewArgument cache mode.
 * @param selected - menu item which was selected.
 */
class WebViewCacheModeMenuArgument(selected: WebViewCacheMode? = null) : EnumMenuArgument<WebViewCacheMode>(selected)