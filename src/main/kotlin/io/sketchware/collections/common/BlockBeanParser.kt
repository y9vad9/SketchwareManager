package io.sketchware.collections.common

import io.sketchware.collections.models.BlockBean
import io.sketchware.serialize
import java.util.regex.Matcher
import java.util.regex.Pattern

object BlockBeanParser {
    private const val regex = "\\{.*?\\}$(?:\\{|\\s|\\Z)"
    private val pattern: Pattern = Pattern.compile(regex, Pattern.MULTILINE)

    /**
     * Parse input string and converts it to list using regex pattern.
     * @param string string of collection file's content.
     */
    fun parseAsArray(string: String): List<BlockBean> {
        val matcher: Matcher = pattern.matcher(string)
        val array = ArrayList<BlockBean>()
        while (matcher.find()) {
            array.add(matcher.group(0).serialize())
        }
        return array
    }
}