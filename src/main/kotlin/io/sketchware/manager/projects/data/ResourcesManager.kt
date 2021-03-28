package io.sketchware.manager.projects.data

import io.sketchware.interfaces.Editor
import io.sketchware.interfaces.listeners.ActionFinishListener
import io.sketchware.models.projects.ProjectResource
import io.sketchware.utils.SketchwareEncryptor.decrypt
import io.sketchware.utils.delegates.lazyInit
import io.sketchware.utils.internal.*
import io.sketchware.utils.internal.TagFormatter
import io.sketchware.utils.internal.TagFormatter.toSaveableValue
import io.sketchware.utils.internal.byteArrayToString
import io.sketchware.utils.internal.read
import io.sketchware.utils.internal.write
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.launch
import java.io.File

class ResourcesManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: MainCoroutineDispatcher = Dispatchers.Main
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) =
            ResourcesManager(file.readOrNull()?.decrypt()?.byteArrayToString() ?: "", file)
    }

    private val imagesDelegate = lazyInit {
        TagFormatter.getListByTag<ProjectResource>("images", value) ?: emptyList()
    }

    /**
     * @return list of [ProjectResource] with images.
     */
    val images by imagesDelegate

    private val fontsDelegate = lazyInit {
        TagFormatter.getListByTag<ProjectResource>("fonts", value) ?: emptyList()
    }

    /**
     * @return list of [ProjectResource] with fonts.
     */
    val fonts by fontsDelegate

    private val soundsDelegate = lazyInit {
        TagFormatter.getListByTag<ProjectResource>("sounds", value) ?: emptyList()
    }

    /**
     * @return list of [ProjectResource] with sounds.
     */
    val sounds by soundsDelegate

    /**
     * Adds image to resource info file.
     * Attention: this method does not add a real picture, but only information about the picture.
     * @param resource - information about image.
     */
    fun addImage(resource: ProjectResource) {
        value = TagFormatter.addTag("images", images.toMutableList().apply {
            add(resource)
        }.toSaveableValue(), value)
        imagesDelegate.reset()
    }

    /**
     * Adds font to resource info file.
     * Attention: this method does not add a real font, but only information about the font.
     * @param resource - information about font.
     */
    fun addFont(resource: ProjectResource) {
        value = TagFormatter.addTag("fonts", fonts.toMutableList().apply {
            add(resource)
        }.toSaveableValue(), value)
        fontsDelegate.reset()
    }

    /**
     * Adds sound to resource info file.
     * Attention: this method does not add a real sound, but only information about the sound.
     * @param resource - information about sound.
     */
    fun addSound(resource: ProjectResource) {
        value = TagFormatter.addTag("sounds", sounds.toMutableList().apply {
            add(resource)
        }.toSaveableValue(), value)
        soundsDelegate.reset()
    }

    /**
     * Removes data about image from project.
     * Attention: this method does not delete the actual file in the project,
     * but only deletes information about the resource (it's name and so on).
     * @param name - name of resource (example: ic_arrow_back).
     */
    fun removeImage(name: String) {
        value = TagFormatter.addTag("images", images.toMutableList().apply {
            removeIf { it.name == name }
        }.toSaveableValue(), value)
        imagesDelegate.reset()
    }

    /**
     * Removes data about font from project.
     * Attention: this method does not delete the actual file in the project,
     * but only deletes information about the resource (it's name and so on).
     * @param name - name of resource (example: google_sans).
     */
    fun removeFont(name: String) {
        value = TagFormatter.addTag("fonts", images.toMutableList().apply {
            removeIf { it.name == name }
        }.toSaveableValue(), value)
        fontsDelegate.reset()
    }

    /**
     * Removes data about sound from project.
     * Attention: this method does not delete the actual file in the project,
     * but only deletes information about the resource (its name and so on).
     * @param name - name of resource (example: some_ringtone).
     */
    fun removeSound(name: String) {
        value = TagFormatter.addTag("sounds", images.toMutableList().apply {
            removeIf { it.name == name }
        }.toSaveableValue(), value)
        soundsDelegate.reset()
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
        imagesDelegate.reset()
        soundsDelegate.reset()
        fontsDelegate.reset()
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
        file.write(value.toByteArray().decrypt())
    }
}