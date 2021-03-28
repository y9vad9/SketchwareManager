package io.sketchware.manager.collections.managers

import io.sketchware.models.collections.MoreblockCollectionItem
import io.sketchware.utils.internal.byteArrayToString
import io.sketchware.utils.internal.read
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