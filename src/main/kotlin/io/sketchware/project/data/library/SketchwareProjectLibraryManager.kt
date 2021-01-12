package io.sketchware.project.data.library

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.models.exceptions.SketchwareFileError
import io.sketchware.models.sketchware.data.BlockDataModel
import io.sketchware.models.sketchware.data.SketchwareLibrary
import io.sketchware.utils.*
import io.sketchware.utils.replaceOrInsertAtTop
import java.io.File

class SketchwareProjectLibraryManager(private val file: File) {
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

    suspend fun getLibraries(): List<SketchwareLibrary> {
        return getList().map {
            SketchwareLibrary(
                it.name, it.values.singleOrNull()?.toModel()
                    ?: error("library don't have any information.")
            )
        }
    }

    /**
     * Edits specific library
     * @param name Name of the library
     */
    suspend fun editLibrary(name: String, builder: SketchwareLibrary.() -> Unit) {
        val libraries = getLibraries().toMutableList()
        val library = libraries.find { it.name == name }
            ?: throw NoSuchElementException("No library with name $name.")
        val newLibrary = library.copy().apply(builder)

        val index = libraries.indexOf(library)
        libraries[index] = newLibrary

        saveLibraries(libraries)
    }

    private suspend fun saveLibraries(list: List<SketchwareLibrary>) {
        val result = list.joinToString("") { library ->
            "@${library.name}\\n${library.information.toJson()}\n\n"
        }
        file.writeFile(FileEncryptor.encrypt(result.toByteArray()))
        this.decryptedString = result
        this.list = null
    }

}