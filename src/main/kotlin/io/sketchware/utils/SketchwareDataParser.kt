package io.sketchware.utils

import io.sketchware.models.sketchware.data.BlockDataModel


object SketchwareDataParser {

    private val regex = Regex("(?<=@).*?(?=\\n@|\$)", RegexOption.DOT_MATCHES_ALL)

    fun parseJsonBlocks(input: String): List<BlockDataModel> {
        val output = ArrayList<BlockDataModel>()
        regex.findAll(input).forEach { matchResult ->
            val name = matchResult.value.substring(
                0, matchResult.value.indexOf("\n")
                    .takeUnless { it == -1 } ?: matchResult.value.length
            )
            output.add(BlockDataModel(name, BlockParser.parseAsArray(matchResult.value)))
        }
        return output
    }

    fun parseTextBlocks(input: String): List<List<String>> {
        return input
            .split("\n")
            .filter { it.contains(":") }
            .map { it.split(":") }
    }

}