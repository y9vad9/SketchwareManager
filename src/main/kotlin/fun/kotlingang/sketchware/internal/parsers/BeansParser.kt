package `fun`.kotlingang.sketchware.internal.parsers

import `fun`.kotlingang.sketchware.internal.extensions.getByTag
import `fun`.kotlingang.sketchware.internal.extensions.normalizeTag
import `fun`.kotlingang.sketchware.internal.extensions.replaceOrInsertAtTop
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

internal object BeansParser {
    private val regex = Regex("\\{.*?}\$(?:\\{|\\s|\\Z)", RegexOption.MULTILINE)

    inline fun <reified T> parseBeans(string: String) =
        parseBeans(string, serializer<T>())

    inline fun <reified T> String.beansToArray() = parseBeans<T>(this)

    fun <T> parseBeans(string: String, serializer: KSerializer<T>) =
        regex.findAll(string)
            .mapNotNull { it.groups[0]?.value?.serialize(serializer) }
            .toList()

    inline fun <reified Model> getListByTag(tag: String, value: String): List<Model>? =
        value.getByTag(tag.normalizeTag())?.let(BeansParser::parseBeans)

    fun parseTextBlocks(input: String) =
        input.lines()
            .filter { it.contains(":") }
            .map { it.split(":") }
            .map { (first, second) -> Pair(first, second) }

    fun addTag(name: String, stringToSave: String, value: String) =
        value.replaceOrInsertAtTop(
            "(@${name.normalizeTag()}.*?)(?=@|\$)".toRegex(),
            "@$name\n$stringToSave\n\n",
            suffix = "\n"
        )

    fun removeTag(name: String, value: String): String {
        return value.replace(
            Regex("(@${name.normalizeTag()}.*?)(?=@|\$)", RegexOption.DOT_MATCHES_ALL),
            "\n"
        )
    }

    inline fun <reified T> List<T>.toSaveableValue() =
        joinToString { "\n${it.deserialize()}" }

}