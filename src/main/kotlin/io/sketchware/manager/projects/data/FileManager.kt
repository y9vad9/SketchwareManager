package io.sketchware.manager.projects.data

import io.sketchware.annotations.ExperimentalSWManagerAPI
import io.sketchware.interfaces.Editor
import io.sketchware.interfaces.listeners.ActionFinishListener
import io.sketchware.models.projects.SketchwareDataFileModel
import io.sketchware.utils.SketchwareEncryptor.decrypt
import io.sketchware.utils.SketchwareEncryptor.encrypt
import io.sketchware.utils.delegates.lazyInit
import io.sketchware.utils.internal.*
import io.sketchware.utils.internal.TagFormatter.toSaveableValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class FileManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) = FileManager(
            file.readOrNull()?.decrypt()?.byteArrayToString() ?: "", file
        )
    }

    private val activitiesDelegate = lazyInit {
        TagFormatter.getListByTag<SketchwareDataFileModel>("activity", value) ?: emptyList()
    }

    /**
     * Gets activities in current scope.
     * @return list of [SketchwareDataFileModel] with data about activity.
     */
    val activities by activitiesDelegate

    private val customViewsDelegate = lazyInit {
        TagFormatter.getListByTag<SketchwareDataFileModel>("customview", value) ?: emptyList()
    }

    /**
     * Gets custom views in specific scope.
     * @return list of [SketchwareDataFileModel] with data about activity.
     */
    val customViews by customViewsDelegate

    /**
     * Adds activity to current scope.
     * @param model - new activity to add.
     */
    fun addActivity(model: SketchwareDataFileModel) {
        value = TagFormatter.addTag("activity", activities.toMutableList().apply {
            add(model)
        }.joinToString("\n") { it.deserialize() }, value)
        activitiesDelegate.reset()
    }

    /**
     * Adds custom view to current scope.
     * @param model - new custom view to add.
     */
    fun addCustomView(model: SketchwareDataFileModel) {
        value = TagFormatter.addTag("customview", customViews.toMutableList().apply {
            add(model)
        }.joinToString("\n") { it.deserialize() }, value)
        customViewsDelegate.reset()
    }

    /**
     * Removes activity from current scope.
     * @param fileName - file name (example: main.xml)
     */
    @ExperimentalSWManagerAPI
    fun removeActivity(fileName: String) {
        value = TagFormatter.addTag("activity", activities.toMutableList().apply {
            removeIf { it.fileName == fileName }
        }.toSaveableValue(), value)
        activitiesDelegate.reset()
    }

    /**
     * Removes customview from current scope.
     * @param fileName - file name (example: customview.xml)
     */
    @ExperimentalSWManagerAPI
    fun removeCustomView(fileName: String) {
        value = TagFormatter.addTag("customview", customViews.toMutableList().apply {
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
        value = file.read().decrypt().byteArrayToString()
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