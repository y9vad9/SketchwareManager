package io.sketchware.manager.projects.data

import io.sketchware.annotation.ExperimentalSWManagerAPI
import io.sketchware.exception.ViewAlreadyExistsException
import io.sketchware.exception.ViewNotFoundException
import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.model.view.WidgetRoot
import io.sketchware.util.SketchwareEncryptor.decrypt
import io.sketchware.util.SketchwareEncryptor.encrypt
import io.sketchware.util.ViewBuilder
import io.sketchware.util.internal.*
import io.sketchware.util.internal.BeansParser.toSaveableValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class ViewManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.Main
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) = ViewManager(
            file.readOrNull()?.byteArrayToString() ?: "", file
        )
    }

    /**
     * Gets view by [viewName] and [widgetName] (if exist).
     * @param viewName - View name (for example: main, without .xml).
     * @param widgetName - specific widget to get (for example: fab).
     * @return list of [WidgetRoot] with data about view.
     */
    fun getView(viewName: String, widgetName: String? = null): List<WidgetRoot>? =
        value.getByTag("$viewName.xml".plus(widgetName?.let { "_$widgetName" } ?: ""))
            ?.let(BeansParser::parseBeans)

    /**
     * Edits view with name [viewName] and [widgetName] (if exist).
     * @param viewName - View name (for example: main, without .xml).
     * @param widgetName - specific widget to get in [viewName].
     * @param widgets - new widgets to save.
     */
    fun editView(
        viewName: String, widgetName: String? = null, widgets: List<WidgetRoot>
    ) = saveView(viewName, widgetName, widgets)

    /**
     * Adds widget to current scope.
     * @param viewName - View name (for example: main, without .xml).
     * @param widgetName - specific widget to get in [viewName].
     * @param widgets - widgets to save.
     * @throws ViewAlreadyExistsException - if view and/or widget with same name already exists.
     */
    @Throws(ViewAlreadyExistsException::class)
    fun addView(
        viewName: String,
        widgetName: String? = null,
        widgets: List<WidgetRoot>
    ) = if (getView(viewName, widgetName) == null)
        editView(viewName, widgetName, widgets)
    else throw ViewAlreadyExistsException(viewName, widgetName)

    /**
     * Adds view to current scope.
     * @throws ViewAlreadyExistsException - if view and/or widget with same name already exists.
     */
    @ExperimentalSWManagerAPI
    @Throws(ViewAlreadyExistsException::class)
    fun addView(builder: ViewBuilder.() -> Unit) = ViewBuilder().apply(builder).apply {
        addView(name, layoutName, widgetBuilder.widgets)
    }

    /**
     * Edits view with name [viewName] and [widgetName] (if exist).
     * @param viewName - View name (for example: main, without .xml).
     * @param widgetName - specific widget to get in [viewName].
     * @param editor - editor.
     */
    fun editView(
        viewName: String,
        widgetName: String? = null,
        editor: (MutableList<WidgetRoot>) -> Unit
    ) = editView(
        viewName,
        widgetName,
        getView(viewName, widgetName)?.toMutableList()?.apply(editor)
            ?: throw ViewNotFoundException(file.path, viewName, widgetName)
    )

    private fun saveView(
        viewName: String,
        widget: String? = null,
        list: List<WidgetRoot>
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

}