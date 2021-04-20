package io.sketchware.model.collection

import io.sketchware.`interface`.CollectionItem
import io.sketchware.model.project.logic.BlockModel
import io.sketchware.model.project.logic.SpecField
import io.sketchware.util.internal.serializer.ListBlockModelSerializer
import io.sketchware.util.internal.serializer.SpecSerializer
import kotlinx.serialization.Serializable

/**
 * Class with data about moreblock collection item.
 */
@Serializable
data class MoreblockCollectionItem(
    /**
     * Name of collection item.
     */
    override val name: String,
    /**
     * Contains data about collection item.
     * For moreblocks it is list of blocks in moreblock logic.
     */
    @Serializable(with = ListBlockModelSerializer::class)
    override val data: List<BlockModel>,
    /**
     * Contains data about collection. Consist only for moreblock.
     * There is spec for moreblock.
     */
    @Serializable(with = SpecSerializer::class)
    override val reserved1: List<SpecField>
) : CollectionItem<List<BlockModel>, List<SpecField>>
