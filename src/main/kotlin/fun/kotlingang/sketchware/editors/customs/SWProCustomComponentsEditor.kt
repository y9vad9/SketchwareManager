package `fun`.kotlingang.sketchware.editors.customs

import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.interfaces.FileExportable
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.json.serialize
import `fun`.kotlingang.sketchware.objects.customs.CustomComponent
import `fun`.kotlingang.sketchware.internal.delegates.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SWProCustomComponentsEditor(
    private var value: String,
    private val file: File
) : CoroutineScope, Editor, FileExportable {
    override val coroutineContext = Dispatchers.IO

    companion object {
        suspend operator fun invoke(file: File) =
            SWProCustomComponentsEditor(file.read().bytesToString(), file)
    }

    private val componentProperty = lazyResetable { value.serialize<List<CustomComponent>>() }

    /**
     * @return List of [CustomComponent] with data about component.
     */
    val components by componentProperty

    /**
     * Adds component to custom components list.
     */
    fun addComponent(component: CustomComponent) = saveComponents(
        components.toMutableList().apply {
            add(component)
        }
    )

    /**
     * Removes component from custom components list.
     */
    fun removeComponent(component: CustomComponent) = saveComponents(
        components.toMutableList().apply {
            remove(component)
        }
    )

    private fun saveComponents(list: List<CustomComponent>) {
        value = list.deserialize()
        componentProperty.reset()
    }

    /**
     * Updates data in Editor async.
     */
    override fun fetch(callback: ActionFinishListener?) = launch {
        fetch()
        callback?.onFinish()
    }

    /**
     * Updates data in Editor.
     */
    override suspend fun fetch() {
        value = file.read().bytesToString()
        componentProperty.reset()
    }

    /**
     * Saves data which was edited async.
     */
    override fun save(callback: ActionFinishListener?) = launch {
        save()
        callback?.onFinish()
    }

    /**
     * Saves data which was edited.
     */
    override suspend fun save() {
        file.write(value.toByteArray())
    }

    /**
     * Imports data from [file].
     * @param file - File with data to import.
     * @param idProvider - Provider for already exist ids.
     * If not specified, already exist will be removed and replaced with new one.
     */
    suspend fun import(
        file: File, idProvider: ((conflictedId: Int) -> Int)? = null
    ) {
        val list = file.read().bytesToString().serialize<List<CustomComponent>>()
        return run {
            val allComponents = components.toMutableList()
            list.forEach { iComponent ->
                if (allComponents.any { it.id == iComponent.id }) {
                    val currentId = iComponent.id
                    iComponent.id = idProvider?.invoke(iComponent.id) ?: iComponent.id
                    if (currentId == iComponent.id)
                        allComponents.removeIf { it.id == currentId }
                }
                saveComponents(list.toMutableList().plus(allComponents))
                componentProperty.reset()
            }
        }
    }

    /**
     * Imports data from [file].
     * @param file - File with data to import.
     * @param callback - call back when import will be finished.
     */
    fun import(file: File, idProvider: (conflictedId: Int) -> Int, callback: ActionFinishListener) = launch {
        import(file, idProvider)
        callback.onFinish()
    }

    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     */
    override suspend fun export(destination: File) {
        destination.write(components.deserialize().toByteArray())
    }

    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     * @param callback - call back when export will be finished.
     */
    override fun export(destination: File, callback: ActionFinishListener) = launch {
        export(destination)
        callback.onFinish()
    }
}