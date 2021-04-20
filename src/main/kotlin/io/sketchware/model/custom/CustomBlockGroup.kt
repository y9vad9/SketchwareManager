package io.sketchware.model.custom

import kotlinx.serialization.Serializable

/**
 * A class with data about a specific group of blocks.
 */
@Serializable
data class CustomBlockGroup(
    /**
     * Blocks group unique identify.
     */
    var groupId: Int,
    /**
     * Block group name
     */
    var name: String,
    /**
     * Blocks group color (shows in the list of block types, example: Variable, Operator, etc.)
     */
    var hexColor: String,
    /**
     * List of custom blocks in specific group.
     */
    var blocks: List<CustomBlock>
) {
    override fun equals(other: Any?): Boolean {
        (other as CustomBlockGroup)
        return groupId == other.groupId
            && name == other.name
            && hexColor == other.hexColor
            && blocks == other.blocks
    }

    override fun hashCode(): Int {
        var result = groupId
        result = 31 * result + name.hashCode()
        result = 31 * result + hexColor.hashCode()
        result = 31 * result + blocks.hashCode()
        return result
    }
}