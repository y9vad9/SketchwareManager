package `fun`.kotlingang.sketchware.editors.collections

import `fun`.kotlingang.sketchware.objects.collections.MoreblockCollectionItem
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import java.io.File


class MoreblockCollectionEditor(
    value: String,
    file: File
) : CollectionEditor<MoreblockCollectionItem>(value, file, MoreblockCollectionItem.serializer()) {
    companion object {
        suspend operator fun invoke(file: File) =
            MoreblockCollectionEditor(file.read().bytesToString(), file)
    }
}