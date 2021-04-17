package io.sketchware.model.collection

import io.sketchware.`interface`.CollectionItem
import io.sketchware.model.project.BlockModel
import io.sketchware.util.serializer.ListBlockModelSerializer
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