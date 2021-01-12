package io.sketchware.project.data.view

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.models.exceptions.SketchwareFileError
import io.sketchware.models.sketchware.data.BlockDataModel
import io.sketchware.models.sketchware.data.SketchwareWidget
import io.sketchware.utils.*
import io.sketchware.utils.replaceOrInsertAtTop
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
     * @param widget specific widget to get (for example: fab).
     */
    suspend fun getView(viewName: String, widget: String? = null): List<SketchwareWidget> {
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

    suspend fun editView(
        viewName: String,
        widget: String? = null,
        builder: ArrayList<SketchwareWidget>.() -> Unit
    ) = saveView(viewName, widget, ArrayList(getView(viewName, widget)).apply(builder))

    private suspend fun saveView(
        viewName: String,
        widget: String? = null,
        list: List<SketchwareWidget>
    ) {
        val name = "$viewName.xml".plus(
            if(widget == null)
                "" else "_$widget"
        )
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$name.*?)(?=@|\$)".toRegex(),
            if (list.isEmpty())
                throw IllegalArgumentException("list cannot be empty")
            else "@$name${BlockParser.toSaveableValue(list)}\n\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        decryptedString = null
    }

}