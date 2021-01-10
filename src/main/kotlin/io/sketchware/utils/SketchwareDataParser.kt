package io.sketchware.utils

import io.sketchware.models.sketchware.data.BlockDataModel


object SketchwareDataParser {

    private val regex = Regex("(?<=@).*?(?=\\n@|\$)", RegexOption.DOT_MATCHES_ALL)

    fun parseJsonBlocks(input: String): List<BlockDataModel> {
        val output = ArrayList<BlockDataModel>()
        regex.findAll(input).forEach {
            val name = it.value.substring(0, it.value.indexOf("\n"))
            output.add(BlockDataModel(name, BlockParser.parseAsArray(it.value)))
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