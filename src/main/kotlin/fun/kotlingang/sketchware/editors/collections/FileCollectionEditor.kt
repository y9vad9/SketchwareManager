package `fun`.kotlingang.sketchware.editors.collections

import `fun`.kotlingang.sketchware.objects.collections.FileCollectionItem
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import java.io.File

class FileCollectionEditor(
    value: String,
    private val file: File
) : CollectionEditor<FileCollectionItem>(value, file, FileCollectionItem.serializer()) {

    companion object {
        suspend operator fun invoke(file: File) =
            FileCollectionEditor(file.read().bytesToString(), file)
    }

    /**
     * Gets file by [name].
     */
    @Suppress("unused")
    fun getFile(name: String) = File(file, "data/$name")
}