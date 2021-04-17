package io.sketchware.manager.collection.managers

import io.sketchware.model.collection.FileCollectionItem
import io.sketchware.util.internal.byteArrayToString
import io.sketchware.util.internal.read
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
    @Suppress("unused")
    fun getFile(name: String) = File(file, "data/$name")
}