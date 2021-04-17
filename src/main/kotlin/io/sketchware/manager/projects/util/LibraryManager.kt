package io.sketchware.manager.projects.util

import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.exception.LibraryNotFoundException
import io.sketchware.model.project.library.LibraryModel
import io.sketchware.model.project.library.SketchwareLibraryDataModel
import io.sketchware.util.SketchwareEncryptor.decrypt
import io.sketchware.util.SketchwareEncryptor.encrypt
import io.sketchware.util.delegate.lazyResetable
import io.sketchware.util.internal.*
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

    private val librariesDelegate = lazyResetable {
        mutableListOf<LibraryModel>().apply {
            Regex("(?<=@).*?(?=\\n@|$)", RegexOption.DOT_MATCHES_ALL)
                .findAll(value).forEach { matchResult ->
                    val name = matchResult.value.substring(
                        0, matchResult.value.indexOf("\n")
                            .takeUnless { it == -1 } ?: matchResult.value.length
                    )
                    add(
                        LibraryModel(
                            name, matchResult.value.replace(name, "").serialize()
                        )
                    )
                }
        }.toList()
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

    private fun saveLibraries(list: List<LibraryModel>) = list.forEach { library ->
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