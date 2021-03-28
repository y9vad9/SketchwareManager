package io.sketchware.models.customs

import io.sketchware.models.projects.SpecField
import io.sketchware.utils.serializers.StringNumberConvertor
import kotlinx.serialization.Contextual
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
    @Serializable(StringNumberConvertor::class)
    var palette: Int,
    @Contextual
    var spec: List<SpecField>,
    var type: String,
    /**
     * Source code of block (it logic).
     */
    var code: String
)