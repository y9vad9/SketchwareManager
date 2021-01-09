package io.sketchware.utils


internal fun String.indexOfOrNull(string: String) = indexOf(string).takeIf { it != -1 }
internal fun String.replaceOrInsertAtTop(regex: Regex, replacement: String): String {
    val content = regex.replace(this, replacement)
    return if(this == content)
        "${replacement}$content"
    else content
}