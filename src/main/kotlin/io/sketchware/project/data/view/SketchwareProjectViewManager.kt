package io.sketchware.project.data.view

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.models.exceptions.SketchwareFileError
import io.sketchware.utils.SketchwareDataParser
import io.sketchware.models.sketchware.data.BlockDataModel
import io.sketchware.models.sketchware.data.SketchwareWidget
import io.sketchware.utils.readFile
import io.sketchware.utils.toModel
import java.io.File

class SketchwareProjectViewManager(private val file: File) {
    private var list: List<BlockDataModel>? = null
    private var decryptedString: String? = null

    init {
        if (!file.isFile)
            throw SketchwareFileError(file.path)
    }

    private suspend fun getDecryptedString(): String {
        if (decryptedString == null)
            decryptedString = String(FileEncryptor.decrypt(file.readFile()))
        return decryptedString ?: error("Decrypted string should be initialized")
    }

    private suspend fun getList(): List<BlockDataModel> {
        if (list == null)
            list = SketchwareDataParser.parseJsonBlocks(getDecryptedString())
        return list ?: error("List shouldn't be null")
    }

    /**
     * Get widgets by activity name.
     * @param viewName Activity Name (example: MainActivity, main)
     */
    suspend fun getWidgets(viewName: String, widget: String? = null): List<SketchwareWidget> {
        return getList().filter {
            it.name == "$viewName.xml".plus(
                if(widget == null)
                    "" else "_$widget"
            )
        }.map { (name, values) ->
            SketchwareWidget(
                name.substring(name.indexOf("_") + 1, name.length),
                values.map { it.toModel() }
            )
        }
    }

}