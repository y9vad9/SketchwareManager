package io.sketchware.collections

import io.sketchware.collections.models.CollectionItem
import io.sketchware.utils.BlockParser
import io.sketchware.utils.readFile
import io.sketchware.utils.toJson
import io.sketchware.utils.writeFile
import java.io.File

/**
 * Util for all collections
 */
class SketchwareCollection(
    private val collectionFile: File,
    private val dataFolder: File = File(collectionFile.parentFile, "data")
) {

    /**
     * Get array of Collection Item with information about items
     */
    suspend fun getCollection(): List<CollectionItem> {
        val moreblocks = String(collectionFile.readFile())
        return BlockParser.parseAsArray(moreblocks)
    }

    /**
     * Get file by name.
     */
    fun getFileByName(name: String): File {
        return File(dataFolder, name)
    }

    suspend fun addItem(collectionItem: CollectionItem) {
        val list = ArrayList(getCollection())
        list.add(collectionItem)
        saveCollection(list)
    }

    suspend fun removeItem(collectionItem: CollectionItem) {
        val list = ArrayList(getCollection())
        list.remove(collectionItem)
        saveCollection(list)
    }

    /**
     * Saves the list you modified to the Sketchware Collections format.
     * @param list your modified list to save
     */
    suspend fun saveCollection(list: List<CollectionItem>) {
        var output = ""
        list.forEach {
            output += "\n${it.toJson()}"
        }
        collectionFile.writeFile(output.toByteArray())
    }

}