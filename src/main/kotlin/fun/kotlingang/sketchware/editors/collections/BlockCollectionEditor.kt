package `fun`.kotlingang.sketchware.editors.collections

import `fun`.kotlingang.sketchware.objects.collections.BlockCollectionItem
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import java.io.File

class BlockCollectionEditor(
    value: String,
    file: File
) : CollectionEditor<BlockCollectionItem>(value, file, BlockCollectionItem.serializer()) {
    companion object {
        suspend operator fun invoke(file: File) =
            BlockCollectionEditor(file.read().bytesToString(), file)
    }
}