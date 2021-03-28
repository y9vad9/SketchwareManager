package io.sketchware.utils.internal

import kotlinx.serialization.KSerializer
import java.util.regex.Matcher
import java.util.regex.Pattern

internal object TagFormatter {
    private const val regex = "\\{.*?\\}$(?:\\{|\\s|\\Z)"
    val pattern: Pattern = Pattern.compile(regex, Pattern.MULTILINE)

    inline fun <reified T> parseAsArray(string: String): List<T> {
        val matcher: Matcher = pattern.matcher(string)
        val array = ArrayList<T>()
        while (matcher.find()) {
            array.add(matcher.group(0).serialize<T>())
        }
        return array
    }

    fun <T> parseAsArray(string: String, serializer: KSerializer<T>): List<T> {
        val matcher: Matcher = pattern.matcher(string)
        val array = ArrayList<T>()
        while (matcher.find()) {
            array.add(matcher.group(0).serialize(serializer))
        }
        return array
    }

    inline fun <reified Model> getListByTag(tag: String, value: String) =
        value.getByTag(tag.normalizeTag())?.let { parseAsArray<Model>(it) }

    fun parseTextBlocks(input: String): List<Pair<String, String>> {
        return input
            .split("\n")
            .filter { it.contains(":") }
            .map { it.split(":") }
            .map { (first, second) -> Pair(first, second) }
    }

    fun addTag(name: String, stringToSave: String, value: String): String {
        return value.replaceOrInsertAtTop(
            "(@${name.replace(".", "\\.")}.*?)(?=@|\$)".toRegex(),
            "@$name$stringToSave\n\n"
        )
    }

    fun removeTag(name: String, value: String): String {
        return value.replace(
            "(@${name.normalizeTag()}.*?)(?=@|\$)".toRegex(),
            "\n"
        )
    }

    inline fun <reified T> List<T>.toSaveableValue() =
        joinToString { "\n${it.deserialize()}" }

}