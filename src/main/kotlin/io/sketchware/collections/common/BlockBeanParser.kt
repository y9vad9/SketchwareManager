package io.sketchware.collections.common

import io.sketchware.collections.models.CollectionItem
import io.sketchware.project.data.BlockParser
import java.util.regex.Pattern

@Deprecated("Use BlockParser instead.",
    ReplaceWith("BlockParser", "io.sketchware.project.data.BlockParser"))
object BlockBeanParser {
    private const val regex = "\\{.*?\\}$(?:\\{|\\s|\\Z)"
    val pattern: Pattern = Pattern.compile(regex, Pattern.MULTILINE)

    /**
     * Parse input string and converts it to list using regex pattern.
     * @param string string of collection file's content.
     * @deprecated Use BlockParser.parseArray(string) instead.
     */
    @Deprecated("Use BlockParser.parseAsArray(string) instead.",
        ReplaceWith("BlockParser.parseAsArray(string)", "io.sketchware.project.data.BlockParser"))
    fun parseAsArray(string: String): List<CollectionItem> = BlockParser.parseAsArray(string)
}