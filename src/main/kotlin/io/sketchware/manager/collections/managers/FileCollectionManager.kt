package io.sketchware.manager.collections.managers

import io.sketchware.models.collections.FileCollectionItem
import io.sketchware.utils.internal.byteArrayToString
import io.sketchware.utils.internal.read
import java.io.File

class FileCollectionManager(
    value: String,
    private val file: File
) : CollectionManager<FileCollectionItem>(value, file, FileCollectionItem.serializer()) {

    companion object {
        suspend operator fun invoke(file: File) =
            FileCollectionManager(file.read().byteArrayToString(), file)
    }

    /**
     * Gets file by [name].
     */
    fun getFile(name: String) = File(file, "data/$name")
}