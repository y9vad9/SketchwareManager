package io.sketchware.manager.customs.components

import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.FileExportable
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.model.custom.CustomComponent
import io.sketchware.util.delegate.lazyResetable
import io.sketchware.util.internal.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class SWProCustomComponentsManager(
    private var value: String,
    private val file: File
) : CoroutineScope, Editor, FileExportable {
    override val coroutineContext = Dispatchers.IO

    companion object {
        suspend operator fun invoke(file: File) =
            SWProCustomComponentsManager(file.read().byteArrayToString(), file)
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
        value = file.read().byteArrayToString()
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
        val list = file.read().byteArrayToString().serialize<List<CustomComponent>>()
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