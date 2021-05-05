package `fun`.kotlingang.sketchware.editors.collections

import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.encrypt
import `fun`.kotlingang.sketchware.interfaces.managers.CollectionManager
import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.internal.extensions.bytesToString
import `fun`.kotlingang.sketchware.internal.extensions.read
import `fun`.kotlingang.sketchware.internal.extensions.write
import `fun`.kotlingang.sketchware.internal.json.deserialize
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser
import `fun`.kotlingang.sketchware.internal.delegates.lazyResetable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.KSerializer
import java.io.File

open class CollectionEditor<Item>(
    private var value: String,
    private val file: File,
    private val serializer: KSerializer<Item>
) : CollectionManager<Item>, Editor, CoroutineScope {

    private val allProperty = lazyResetable {
        BeansParser.parseBeans(value, serializer)
    }

    /**
     * @return list of all collection items.
     */
    override val all: List<Item> by allProperty

    /**
     * Adds item to collection
     * @param entity - Entity to add.
     */
    override fun addItem(entity: Item) = saveItems(
        all.toMutableList().apply { add(entity) }
    )

    /**
     * Removes item which matches [entity].
     */
    override fun removeItem(entity: Item) = saveItems(
        all.toMutableList().apply { remove(entity) }
    )

    /**
     * Saves [items] locally to [value].
     */
    private fun saveItems(items: List<Item>) {
        value = items.joinToString("\n") { it.deserialize(serializer) }
        allProperty.reset()
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
        value = file.read().decrypt().bytesToString()
        allProperty.reset()
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
        file.write(value.toByteArray().encrypt())
    }

    override val coroutineContext = Dispatchers.Main

}