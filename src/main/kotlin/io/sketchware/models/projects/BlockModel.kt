package io.sketchware.models.projects

import io.sketchware.utils.serializers.SpecSerializer
import io.sketchware.utils.serializers.StringNumberConvertor
import kotlinx.serialization.Serializable

/**
 * The class stores data about a block that is in some kind of logic.
 */
@Serializable
data class BlockModel(
    /**
     * Block color in [Int] type.
     */
    var color: Int,
    /**
     * Block unique id (in specific scope)
     */
    @Serializable(StringNumberConvertor::class)
    var id: Int,
    /**
     * Next block id or '-1' if it doesn't have next block.
     */
    var nextBlock: Int,
    /**
     * Unique block code.
     */
    var opCode: String,
    /**
     * Arguments of block function.
     */
    var parameters: List<String>,
    /**
     * List of [SpecField] with info about block and it's arguments.
     */
    @Serializable(with = SpecSerializer::class)
    var spec: List<SpecField>,
    var subStack1: Int,
    var subStack2: Int,
    /**
     * Name of block type.
     */
    var type: String,
    /**
     * Name of type.
     */
    var typeName: String
)