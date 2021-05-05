package `fun`.kotlingang.sketchware.editors.project

import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.internal.delegates.lazyResetable
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.readOrNull
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser.toSaveableValue
import `fun`.kotlingang.sketchware.objects.project.resources.ProjectResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class ResourcesEditor(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineDispatcher = Dispatchers.IO
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) =
            ResourcesEditor(file.readOrNull()?.decrypt()?.bytesToString() ?: "", file)
    }

    private val imagesDelegate = lazyResetable {
        BeansParser.getListByTag<ProjectResource>("images", value) ?: emptyList()
    }

    /**
     * @return list of [ProjectResource] with images.
     */
    val images by imagesDelegate

    private val fontsDelegate = lazyResetable {
        BeansParser.getListByTag<ProjectResource>("fonts", value) ?: emptyList()
    }

    /**
     * @return list of [ProjectResource] with fonts.
     */
    val fonts by fontsDelegate

    private val soundsDelegate = lazyResetable {
        BeansParser.getListByTag<ProjectResource>("sounds", value) ?: emptyList()
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
        value = BeansParser.addTag("images", images.toMutableList().apply {
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
        value = BeansParser.addTag("fonts", fonts.toMutableList().apply {
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
        value = BeansParser.addTag("sounds", sounds.toMutableList().apply {
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
        value = BeansParser.addTag("images", images.toMutableList().apply {
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
        value = BeansParser.addTag("fonts", images.toMutableList().apply {
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
        value = BeansParser.addTag("sounds", images.toMutableList().apply {
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
        value = file.read().decrypt().bytesToString()
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