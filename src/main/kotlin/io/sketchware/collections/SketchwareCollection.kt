package io.sketchware.collections

import io.sketchware.collections.common.BlockBeanParser
import io.sketchware.collections.models.BlockBean
import io.sketchware.readFile
import io.sketchware.toJson
import io.sketchware.writeFile
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
    suspend inline fun getArray(): List<BlockBean> {
        val moreblocks = String(collectionFile.readFile())
        return BlockBeanParser.parseAsArray(moreblocks)
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
    suspend fun save(list: List<BlockBean>) {
        var output = ""
        list.forEach {
            output += "\n${it.toJson()}"
        }
        collectionFile.writeFile(output.toByteArray())
    }

}