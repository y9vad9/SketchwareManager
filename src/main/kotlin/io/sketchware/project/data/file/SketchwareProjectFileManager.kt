package io.sketchware.project.data.file

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.models.exceptions.SketchwareFileError
import io.sketchware.models.sketchware.data.BlockDataModel
import io.sketchware.models.sketchware.data.SketchwareDataFile
import io.sketchware.utils.*
import io.sketchware.utils.replaceOrInsertAtTop
import java.io.File

class SketchwareProjectFileManager(private val file: File) {
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

    suspend fun getActivities(): List<SketchwareDataFile> {
        return getList().find {
            it.name == "activity"
        }?.values?.map { it.toModel() }!!
    }

    suspend fun getCustomViews(): List<SketchwareDataFile> {
        return getList().find {
            it.name == "customview"
        }?.values?.map { it.toModel() }!!
    }

    private suspend fun save(title: String, list: List<SketchwareDataFile>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$title.*?)(?=@|\$)".toRegex(),
            if (list.isEmpty())
                ""
            else "@$title${BlockParser.toSaveableValue(list)}\n"
        )
        file.writeFile(getDecryptedString().toByteArray())
        this.list = null
        this.decryptedString = null
    }

    suspend fun addActivity(activity: SketchwareDataFile) {
        val list = ArrayList(getActivities())
        list.add(activity)
        save("activity", list)
    }

    suspend fun addCustomView(customView: SketchwareDataFile) {
        val list = ArrayList(getCustomViews())
        list.add(customView)
        save("customview", list)
    }

}