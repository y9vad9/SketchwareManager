package io.sketchware.manager.collections.managers

import io.sketchware.models.collections.BlockCollectionItem
import io.sketchware.utils.internal.byteArrayToString
import io.sketchware.utils.internal.read
import java.io.File

class BlockCollectionManager(
    value: String,
    file: File
) : CollectionManager<BlockCollectionItem>(value, file, BlockCollectionItem.serializer()) {
    companion object {
        suspend operator fun invoke(file: File) =
            BlockCollectionManager(file.read().byteArrayToString(), file)
    }
}