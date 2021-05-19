package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.internal.json.serializers.BlockTypeSerializer
import `fun`.kotlingang.sketchware.internal.json.serializers.SpecSerializer
import `fun`.kotlingang.sketchware.internal.json.serializers.StringNumberConvertor
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.BlockSpec
import kotlinx.serialization.Serializable

/**
 * The class stores data about a block that is in some kind of logic.
 */
@Serializable
data class BlockProperties(
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
    var spec: List<BlockSpec>,
    /**
     * Says position in first sub stack (if or if-else style blocks) or
     * has value `-1` if it does not exist in first sub stack.
     */
    var subStack1: Int,
    /**
     * Says position in second sub stack (if or if-else style blocks) or
     * has value `-1` if it does not exist in second sub stack.
     */
    var subStack2: Int,
    /**
     * Name of block type.
     */
    @Serializable(with = BlockTypeSerializer::class)
    var type: BlockType,
    /**
     * Name of type.
     */
    var typeName: String
)