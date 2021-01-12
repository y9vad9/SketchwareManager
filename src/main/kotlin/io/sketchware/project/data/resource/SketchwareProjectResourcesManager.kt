package io.sketchware.project.data.resource

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.models.exceptions.SketchwareFileError
import io.sketchware.utils.SketchwareDataParser
import io.sketchware.models.sketchware.data.BlockDataModel
import io.sketchware.models.sketchware.data.SketchwareProjectResource
import io.sketchware.utils.readFile
import io.sketchware.utils.toModel
import java.io.File

class SketchwareProjectResourcesManager(private val file: File) {
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

    suspend fun getImages(): List<SketchwareProjectResource>? {
        return getList().find {
            it.name == "images"
        }?.values?.map { it.toModel() }
    }

    suspend fun getSounds(): List<SketchwareProjectResource>? {
        return getList().find {
            it.name == "sounds"
        }?.values?.map { it.toModel() }
    }

    suspend fun getFonts(): List<SketchwareProjectResource>? {
        return getList().find {
            it.name == "images"
        }?.values?.map { it.toModel() }
    }

}