package io.sketchware.util.internal

internal fun String.replaceOrInsertAtTop(
    regex: Regex,
    replacement: String,
    prefix: String = "",
    suffix: String = ""
): String {
    val content = regex.replace(this, replacement)
    return if (this == content)
        "${replacement}$content"
    else "${prefix}content${suffix}"
}

internal fun String.getByTag(tag: String): String? {
    val regex = Regex(
        "(?<=@)($tag\\b)(.*?)(?=\\n@|$)",
        RegexOption.DOT_MATCHES_ALL
    )
    val result = regex
        .find(this)
    return result?.value
}

internal fun String.normalizeTag() = this.replace(".", "\\.")

internal val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
internal val snakeRegex = "_[a-zA-Z]".toRegex()

internal fun String.camelToSnakeCase(): String {
    return camelRegex.replace(this) {
        "_${it.value}"
    }.toLowerCase()
}

internal fun String.snakeToLowerCamelCase(): String {
    return snakeRegex.replace(this) {
        it.value.replace("_", "")
            .toUpperCase()
    }
}

internal fun String.snakeToUpperCamelCase(): String {
    return this.snakeToLowerCamelCase().capitalize()
}