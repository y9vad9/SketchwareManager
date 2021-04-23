package `fun`.kotlingang.sketchware.editors.project

import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.encrypt
import `fun`.kotlingang.sketchware.interfaces.Editor
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.readOrNull
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser.toSaveableValue
import `fun`.kotlingang.sketchware.objects.project.content.FileDataModel
import io.sketchware.util.delegate.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class ContentEditor(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) = ContentEditor(
            file.readOrNull()?.decrypt()?.bytesToString() ?: "", file
        )
    }

    private val activitiesDelegate = lazyResetable {
        BeansParser.getListByTag<FileDataModel>("activity", value) ?: emptyList()
    }

    /**
     * Gets activities in current scope.
     * @return list of [FileDataModel] with data about activity.
     */
    val activities by activitiesDelegate

    private val customViewsDelegate = lazyResetable {
        BeansParser.getListByTag<FileDataModel>("customview", value) ?: emptyList()
    }

    /**
     * Gets custom views in specific scope.
     * @return list of [FileDataModel] with data about activity.
     */
    val customViews by customViewsDelegate

    /**
     * Adds activity to current scope.
     * @param model - new activity to add.
     */
    fun addActivity(model: FileDataModel) {
        value = BeansParser.addTag("activity", activities.toMutableList().apply {
            add(model)
        }.joinToString("\n") { it.deserialize() }, value)
        activitiesDelegate.reset()
    }

    /**
     * Adds custom view to current scope.
     * @param model - new custom view to add.
     */
    fun addCustomView(model: FileDataModel) {
        value = BeansParser.addTag("customview", customViews.toMutableList().apply {
            add(model)
        }.joinToString("\n") { it.deserialize() }, value)
        customViewsDelegate.reset()
    }

    /**
     * Removes activity from current scope.
     * @param fileName - file name (example: main.xml)
     */
    @`fun`.kotlingang.sketchware.annotations.ExperimentalSWManagerAPI
    fun removeActivity(fileName: String) {
        value = BeansParser.addTag("activity", activities.toMutableList().apply {
            removeIf { it.fileName == fileName }
        }.toSaveableValue(), value)
        activitiesDelegate.reset()
    }

    /**
     * Removes customview from current scope.
     * @param fileName - file name (example: customview.xml)
     */
    @`fun`.kotlingang.sketchware.annotations.ExperimentalSWManagerAPI
    fun removeCustomView(fileName: String) {
        value = BeansParser.addTag("customview", customViews.toMutableList().apply {
            removeIf { it.fileName == fileName }
        }.toSaveableValue(), value)
        customViewsDelegate.reset()
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
        activitiesDelegate.reset()
        customViewsDelegate.reset()
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