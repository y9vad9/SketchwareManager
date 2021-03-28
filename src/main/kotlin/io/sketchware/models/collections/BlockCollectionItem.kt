package io.sketchware.models.collections

import io.sketchware.interfaces.CollectionItem
import io.sketchware.models.projects.BlockModel
import io.sketchware.utils.serializers.ListBlockModelSerializer
import kotlinx.serialization.Serializable

/**
 * Class with data about moreblock collection item.
 */
@Serializable
data class BlockCollectionItem(
    /**
     * Name of collection item.
     */
    override val name: String,
    /**
     * Contains data about collection item.
     * For moreblocks it is list of blocks in moreblock logic.
     */
    @Serializable(ListBlockModelSerializer::class)
    override val data: List<BlockModel>,
    /**
     * Does not contain anything for this type of collection.
     */
    override val reserved1: String? = null
) : CollectionItem<List<BlockModel>, String>