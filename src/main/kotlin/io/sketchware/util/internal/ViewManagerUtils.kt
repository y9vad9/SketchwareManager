package io.sketchware.util.internal

import io.sketchware.model.project.view.WidgetProperties
import io.sketchware.model.project.view.WidgetType
import io.sketchware.model.project.widget.*

/**
 * Utils for [io.sketchware.manager.projects.util.ViewManager].
 */
internal object ViewManagerUtils {

    /**
     * @return list of children in specific parent.
     * @param [parentId] - parent layout id.
     */
    fun List<WidgetProperties>.getChildrenOf(parentId: String): List<BaseWidget> {
        return filter { it.parent == parentId }.map {
            it.toWidget(getChildrenProvider())
        }
    }

    private fun List<WidgetProperties>.getChildrenProvider() = { id: String ->
        getChildrenOf(id)
    }

    /**
     * Converts [RootView] with children to sketchware-formatted list.
     * @return list with sketchware-formatted list.
     */
    fun RootView.toSketchwareFormat() =
        children.map { it.toSketchwareFormat() }.flatten()

    /**
     * Converts [BaseWidget] to list with widget properties (sketchware format).
     * @return [List] with widgets properties.
     */
    fun BaseWidget.toSketchwareFormat(): List<WidgetProperties> {
        return if (this is ViewGroupWidget)
            children.map { it.toSketchwareFormat() }.flatten()
        else listOf(this.view)
    }

    private fun WidgetProperties.toWidget(
        childrenProvider: (String) -> List<BaseWidget>
    ) = if (!canHaveChildren)
        toNonChildableWidget()
    else getChildableWidget(childrenProvider)

    private fun WidgetProperties.getChildableWidget(
        childrenProvider: (String) -> List<BaseWidget>
    ) = when (widgetType) {
        WidgetType.HORIZONTAL_SCROLL -> ScrollViewWidget(
            this, childrenProvider(id).toMutableList()
        )
        WidgetType.VERTICAL_SCROLL -> ScrollViewWidget(
            this, childrenProvider(id).toMutableList()
        )
        WidgetType.LINEAR_LAYOUT -> LinearLayoutWidget(
            this, childrenProvider(id).toMutableList()
        )
        else -> error("Widget with id $id can not have child elements.")
    }

    private fun WidgetProperties.toNonChildableWidget() = when (widgetType) {
        WidgetType.TEXTVIEW -> TextViewWidget(this)
        WidgetType.EDITTEXT -> EditTextWidget(this)
        WidgetType.BUTTON -> ButtonWidget(this)
        WidgetType.CALENDAR -> CalendarViewWidget(this)
        WidgetType.CHECKBOX -> CheckboxWidget(this)
        WidgetType.IMAGEVIEW -> ImageViewWidget(this)
        WidgetType.MAPVIEW -> MapViewWidget(this)
        WidgetType.SWITCH -> SwitchWidget(this)
        WidgetType.LISTVIEW -> ListViewWidget(this)
        WidgetType.PROGRESSBAR -> ProgressBarWidget(this)
        WidgetType.SEEKBAR -> SeekBarWidget(this)
        WidgetType.SPINNER -> SpinnerWidget(this)
        WidgetType.WEBVIEW -> WebViewWidget(this)
        WidgetType.ADVIEW -> WebViewWidget(this)
        else -> BaseWidget(this)
    }
}