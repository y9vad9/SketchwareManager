package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

sealed class ViewGroupWidget(
    view: WidgetProperties,
    private val _children: MutableList<BaseWidget>
) : BaseWidget(view) {

    /**
     * Contains data about layout orientation.
     */
    var orientation by layout::orientation

    /**
     * Contains data about widget gravity
     */
    var gravity by layout::gravity

    /**
     * Widget children widgets.
     * @return [List] with [BaseWidget] items.
     */
    val children get() = _children
}