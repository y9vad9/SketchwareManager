package io.sketchware.model.custom

import io.sketchware.model.project.logic.SpecField
import io.sketchware.util.serializer.SpecSerializer
import io.sketchware.util.serializer.StringNumberConvertor
import kotlinx.serialization.Serializable

@Serializable
data class CustomBlock(
    var typeName: String,
    /**
     * Hexadecimal string with block's color.
     */
    var color: String,
    /**
     * Block name
     */
    var name: String,
    /**
     * It stores data about the group that contains the block.
     */
    @Serializable(with = StringNumberConvertor::class)
    var palette: Int,
    @Serializable(with = SpecSerializer::class)
    var spec: List<SpecField>,
    var type: String,
    /**
     * Source code of block (it logic).
     */
    var code: String
)