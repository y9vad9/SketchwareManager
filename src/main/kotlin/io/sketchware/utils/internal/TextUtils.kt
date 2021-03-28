package io.sketchware.utils.internal

internal fun String.replaceOrInsertAtTop(regex: Regex, replacement: String): String {
    val content = regex.replace(this, replacement)
    return if (this == content)
        "${replacement}$content"
    else content
}

internal fun String.getByTag(tag: String): String? {
    val tagNormalized = tag.replace(".", "\\.")
    val regex = Regex(
        "(?<=@)($tagNormalized\\b)(.*?)(?=\\n@|$)",
        RegexOption.DOT_MATCHES_ALL
    )
    val result = regex
        .find(this)
    return result?.value
}

internal fun String.normalizeTag() = this.replace(".", "\\.")