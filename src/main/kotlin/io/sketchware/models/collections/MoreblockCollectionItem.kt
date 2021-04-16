package io.sketchware.models.collections

import io.sketchware.interfaces.CollectionItem
import io.sketchware.models.projects.BlockModel
import io.sketchware.models.projects.SpecField
import io.sketchware.utils.serializers.ListBlockModelSerializer
import io.sketchware.utils.serializers.SpecSerializer
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
