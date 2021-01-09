package io.sketchware.project.data.library

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.exceptions.projects.SketchwareFileError
import io.sketchware.project.data.models.BlockDataModel
import io.sketchware.project.data.models.SketchwareLibrary
import io.sketchware.utils.readFile
import io.sketchware.utils.serialize
import io.sketchware.utils.toModel
import java.io.File

class SketchwareProjectLibraryManager(private val file: File) {
    private var list: List<BlockDataModel>? = null
    private var decryptedString: String? = null

    init {
        if(!file.isFile)
            throw SketchwareFileError(file.path)
    }

    private suspend fun getDecryptedString(): String {
        if(decryptedString == null)
            decryptedString = String(FileEncryptor.decrypt(file.readFile()))
        return decryptedString ?: error("Decrypted string should be initialized")
    }

    private suspend fun getList(): List<BlockDataModel> {
        if(list == null)
            list = getDecryptedString().serialize()
        return list ?: error("List shouldn't be null")
    }

    suspend fun getLibraries(): List<SketchwareLibrary> {
        return getList().map {
            SketchwareLibrary(it.name, it.values.singleOrNull()?.toModel()
                ?: error("library don't have any information."))
        }
    }

}