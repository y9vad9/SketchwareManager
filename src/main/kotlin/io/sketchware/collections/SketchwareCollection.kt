package io.sketchware.collections

import io.sketchware.collections.models.CollectionItem
import io.sketchware.project.data.BlockParser
import io.sketchware.utils.readFile
import io.sketchware.utils.toJson
import io.sketchware.utils.writeFile
import java.io.File

/**
 * Util for all collections
 */
class SketchwareCollection(
    val collectionFile: File,
    private val dataFolder: File = File(collectionFile.parentFile, "data")
) {

    /**
     * Get array of BlockBean with information about items
     */
    suspend inline fun getArray(): List<CollectionItem> {
        val moreblocks = String(collectionFile.readFile())
        return BlockParser.parseAsArray(moreblocks)
    }

    /**
     * Get file by name.
     */
    fun getFileByName(name: String): File {
        return File(dataFolder, name)
    }

    /**
     * Saves the list you modified to the Sketchware Collections format.
     * @param list your modified list to save
     */
    suspend fun save(list: List<CollectionItem>) {
        var output = ""
        list.forEach {
            output += "\n${it.toJson()}"
        }
        collectionFile.writeFile(output.toByteArray())
    }

}