package io.sketchware.manager.collection.managers

import io.sketchware.model.collection.MoreblockCollectionItem
import io.sketchware.util.internal.byteArrayToString
import io.sketchware.util.internal.read
import java.io.File


class MoreblockCollectionManager(
    value: String,
    file: File
) : CollectionManager<MoreblockCollectionItem>(value, file, MoreblockCollectionItem.serializer()) {
    companion object {
        suspend operator fun invoke(file: File) =
            MoreblockCollectionManager(file.read().byteArrayToString(), file)
    }
}