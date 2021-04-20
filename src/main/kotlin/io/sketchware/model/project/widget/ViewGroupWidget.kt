package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

open class ViewGroupWidget internal constructor(
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