package `fun`.kotlingang.sketchware.objects.collections

import `fun`.kotlingang.sketchware.interfaces.CollectionItem
import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.SpecField
import `fun`.kotlingang.sketchware.internal.json.serializers.ListBlockModelSerializer
import `fun`.kotlingang.sketchware.internal.json.serializers.SpecSerializer
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
