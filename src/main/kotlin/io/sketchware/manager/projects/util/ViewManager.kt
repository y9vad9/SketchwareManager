package io.sketchware.manager.projects.util

import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.model.project.view.WidgetProperties
import io.sketchware.model.project.widget.*
import io.sketchware.util.SketchwareEncryptor.decrypt
import io.sketchware.util.SketchwareEncryptor.encrypt
import io.sketchware.util.internal.*
import io.sketchware.util.internal.BeansParser.removeTag
import io.sketchware.util.internal.BeansParser.toSaveableValue
import io.sketchware.util.internal.ViewManagerUtils.getChildrenOf
import io.sketchware.util.internal.ViewManagerUtils.toSketchwareFormat
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.CoroutineContext

class ViewManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : CoroutineScope, Editor {

    /**
     * Stores [Set] with [View] which was got from [getView].
     * Using to save changes which was done in [View] controller.
     */
    private val views: MutableSet<View> by lazy { mutableSetOf() }

    companion object {
        suspend operator fun invoke(file: File) = ViewManager(
            file.readOrNull()?.byteArrayToString() ?: "", file
        )
    }

    /**
     * Gets view by [viewName] and [widgetName] (if specified).
     * @param viewName - view name (for example: main).
     * @param widgetName - widget name (for example: fab).
     * @return [View] with view controller.
     */
    fun getView(viewName: String, widgetName: String? = null): View? {
        val name = "$viewName.xml".plus(widgetName?.let { "_$widgetName" } ?: "")
        views.find { it.viewName == name }
            ?.let { return it }

        val widgets = value.getByTag(name)
            ?.let { BeansParser.parseBeans<WidgetProperties>(it) } ?: return null

        val view = View(viewName, widgets.toMutableList())
        views.add(view)
        return view
    }

    /**
     * Saves data locally to [value].
     */
    private fun saveAllLocally() = views.forEach { view ->
        view.save()
    }

    /**
     * Class which contains data about some view.
     * @see [getView].
     */
    inner class View internal constructor(
        private var _viewName: String,
        private val list: MutableList<WidgetProperties>
    ) {

        /**
         * Gets list of widgets in root.
         * @return [RootView] with widgets.
         */
        val root get() = RootView(list.getChildrenOf("root").toMutableList())

        /**
         * Removes view in [ViewManager].
         * The View will be removed and it will no longer be possible
         * to get it outside of this Instance. You can call [save] later to bring the View back.
         */
        fun remove() {
            value = removeTag(viewName, value)
        }

        /**
         * Edits view name.
         * It will replace old one with new one in [ViewManager] and here locally.
         */
        var viewName get() = _viewName
            set(value) {
                _viewName = value
                this@ViewManager.value = this@ViewManager.value.replace("@$viewName", "@$value")
            }

        /**
         * Saves view in to [ViewManager].
         * If view was removed (by [remove]) it will turn it back.
         */
        fun save() {
            value = BeansParser.addTag(
                viewName,
                root.toSketchwareFormat().joinToString("\n") { it.deserialize() },
                value
            )
        }

    }

    private fun saveView(
        viewName: String,
        widget: String? = null,
        list: List<WidgetProperties>
    ) = synchronized(this) {
        val name = "$viewName.xml".plus(
            if (widget == null)
                "" else "_$widget"
        )
        value = value.replaceOrInsertAtTop(
            "(@$name.*?)(?=@|\$)".toRegex(),
            if (list.isEmpty())
                throw IllegalStateException("list cannot be empty")
            else "@$name${list.toSaveableValue()}\n\n"
        )
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
        value = file.read().decrypt().byteArrayToString()
        views.clear()
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
        withContext(GlobalScope.coroutineContext) {
            saveAllLocally()
        }
        file.write(value.toByteArray().encrypt())
    }

}