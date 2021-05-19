package `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.webview

import kotlinx.serialization.Serializable

@Serializable
enum class WebViewCacheMode {
    LOAD_DEFAULT, LOAD_CACHE_ELSE_NETWORK, LOAD_NO_CACHE, LOAD_CACHE_ONLY
}