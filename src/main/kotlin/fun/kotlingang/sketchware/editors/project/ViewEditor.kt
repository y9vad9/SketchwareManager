package `fun`.kotlingang.sketchware.editors.project

import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.decrypt
import `fun`.kotlingang.sketchware.encryptor.SketchwareEncryptor.encrypt
import `fun`.kotlingang.sketchware.interfaces.callbacks.ActionFinishListener
import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.internal.extensions.*
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser.removeTag
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser.toSaveableValue
import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties
import `fun`.kotlingang.sketchware.objects.project.view.widgets.RootView
import kotlinx.coroutines.*
import java.io.File
import kotlin.coroutines.CoroutineContext

class ViewEditor(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.IO
) : CoroutineScope, Editor {

    /**
     * Stores [Set] with [View] which was got from [getView].
     * Using to save changes which was done in [View] controller.
     */
    private val views: MutableSet<View> by lazy { mutableSetOf() }

    companion object {
        suspend operator fun invoke(file: File) = ViewEditor(
            file.readOrNull()?.bytesToString() ?: "", file
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
        saveView(view.viewName, view.root.toSketchwareFormat())
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
         * Removes view in [ViewEditor].
         * The ViewArgument will be removed and it will no longer be possible
         * to get it outside of this Instance. You can call [save] later to bring the ViewArgument back.
         * @return [Boolean] - `true` if successfully removed from [views]
         * and [value] or `false` if it wasn't deleted (for example, if it already deleted).
         */
        fun remove(): Boolean {
            value = removeTag(viewName, value)
            return views.remove(this)
        }

        /**
         * Edits view name.
         * It will replace old one with new one in [ViewEditor] and here locally.
         */
        var viewName
            get() = _viewName
            set(value) {
                _viewName = value
                this@ViewEditor.value = this@ViewEditor.value.replace("@$viewName", "@$value")
            }

        /**
         * Mark view as changed.
         * If view was removed (by [remove]) it will turn it back.
         * No need to call it, if you aren't removed view before.
         */
        fun notifyChanged() {
            views.add(this)
        }

    }

    private fun saveView(
        viewName: String,
        list: List<WidgetProperties>
    ) = synchronized(this) {
        value = value.replaceOrInsertAtTop(
            "(@${viewName.normalizeTag()}.*?)(?=@|\$)".toRegex(),
            if (list.isEmpty())
                throw IllegalStateException("list cannot be empty")
            else "@$viewName${list.toSaveableValue()}\n\n"
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
        value = file.read().decrypt().bytesToString()
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