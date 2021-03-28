package io.sketchware.manager.projects.data

import io.sketchware.exceptions.LibraryNotFoundException
import io.sketchware.interfaces.Editor
import io.sketchware.interfaces.listeners.ActionFinishListener
import io.sketchware.models.projects.SketchwareLibraryDataModel
import io.sketchware.models.projects.SketchwareLibraryModel
import io.sketchware.utils.SketchwareEncryptor.decrypt
import io.sketchware.utils.SketchwareEncryptor.encrypt
import io.sketchware.utils.delegates.lazyInit
import io.sketchware.utils.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class LibraryManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) =
            LibraryManager(file.readOrNull()?.decrypt()?.byteArrayToString() ?: "", file)
    }

    private val librariesDelegate = lazyInit {
        mutableListOf<SketchwareLibraryModel>().apply {
            Regex("(?<=@).*?(?=\\\\n@|\\\$)", RegexOption.DOT_MATCHES_ALL)
                .findAll(value).forEach { matchResult ->
                    val name = matchResult.value.substring(
                        0, matchResult.value.indexOf("\n")
                            .takeUnless { it == -1 } ?: matchResult.value.length
                    )
                    add(
                        SketchwareLibraryModel(
                            name, matchResult.value.replace(name, "").serialize()
                        )
                    )
                }
        }
    }

    /**
     * @return all libraries in scope.
     */
    val libraries by librariesDelegate

    /**
     * Edits data about library.
     * @param name - library name.
     * @param editor - lambda with [SketchwareLibraryDataModel] in context to edit.
     * @throws LibraryNotFoundException if library does not exist.
     */
    @Throws(LibraryNotFoundException::class)
    fun editLibraryData(
        name: String,
        editor: SketchwareLibraryDataModel.() -> Unit
    ) {
        val librariesList = libraries.toMutableList()
        val libraryIndex = librariesList.indexOfFirst { it.name == name }
            .takeIf { it != -1 } ?: throw LibraryNotFoundException(name)
        librariesList[libraryIndex].information = librariesList[libraryIndex].information.apply(editor)
    }

    private fun saveLibraries(list: List<SketchwareLibraryModel>) = list.forEach { library ->
        value = ""
        value += "@${library.name}\n${library.deserialize()}\n\n"
        librariesDelegate.reset()
    }

    /**
     * Updates data in FileManager by reading [file].
     * @param callback - will be called when the fetch is complete.
     */
    override fun fetch(callback: ActionFinishListener?) = launch {
        fetch()
        callback?.onFinish()
    }

    /**
     * Updates data in FileManager by reading [file].
     */
    override suspend fun fetch() {
        value = file.read().decrypt().byteArrayToString()
        librariesDelegate.reset()
    }

    /**
     * Saves data from [value] into [file].
     * @param callback - will be called when the fetch is complete.
     */
    override fun save(callback: ActionFinishListener?) = launch {
        save()
        callback?.onFinish()
    }

    /**
     * Saves data from [value] into [file].
     */
    override suspend fun save() {
        file.write(value.toByteArray().encrypt())
    }

}