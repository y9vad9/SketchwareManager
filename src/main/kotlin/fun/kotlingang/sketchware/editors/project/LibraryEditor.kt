package `fun`.kotlingang.sketchware.editors.project

import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.encrypt
import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.exceptions.LibraryNotFoundException
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.readOrNull
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.objects.project.library.LibraryModel
import `fun`.kotlingang.sketchware.objects.project.library.LibraryDataModel
import `fun`.kotlingang.sketchware.internal.delegates.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class LibraryEditor(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) =
            LibraryEditor(file.readOrNull()?.decrypt()?.bytesToString() ?: "", file)
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
     * @param editor - lambda with [LibraryDataModel] in context to edit.
     * @throws LibraryNotFoundException if library does not exist.
     */
    @Throws(LibraryNotFoundException::class)
    fun editLibraryData(
        name: String,
        editor: LibraryDataModel.() -> Unit
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
        value = file.read().decrypt().bytesToString()
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