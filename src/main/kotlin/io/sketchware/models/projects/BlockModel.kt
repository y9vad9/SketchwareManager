package io.sketchware.models.projects

import io.sketchware.utils.serializers.StringNumberConvertor
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * The class stores data about a block that is in some kind of logic.
 */
@Serializable
data class BlockModel(
    /**
     * Block color in [Int] type.
     */
    val color: Int,
    /**
     * Block unique id (in specific scope)
     */
    @Serializable(StringNumberConvertor::class)
    val id: Int,
    /**
     * Next block id or '-1' if it doesn't have next block.
     */
    val nextBlock: Int,
    /**
     * Unique block code.
     */
    val opCode: String,
    /**
     * Arguments of block function.
     */
    val parameters: List<String>,
    /**
     * List of [SpecField] with info about block and it's arguments.
     */
    @Contextual
    val spec: List<SpecField>,
    val subStack1: Int,
    val subStack2: Int,
    /**
     * Name of block type.
     */
    val type: String,
    /**
     * Name of type.
     */
    val typeName: String
)