package io.sketchware.manager.collection.managers

import io.sketchware.model.collection.BlockCollectionItem
import io.sketchware.util.internal.byteArrayToString
import io.sketchware.util.internal.read
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