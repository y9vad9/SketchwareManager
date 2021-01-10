package io.sketchware.utils

internal fun String.replaceOrInsertAtTop(regex: Regex, replacement: String): String {
    val content = regex.replace(this, replacement)
    return if (this == content)
        "${replacement}$content"
    else content
}