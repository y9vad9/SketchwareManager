package io.sketchware.util

import io.sketchware.model.project.view.WidgetProperties

class Widgets internal constructor(
    private val rootId: String? = null,
    private val list: MutableList<WidgetProperties>
) {
    /**
     * Makes one item out of a list of widgets with one item.
     * @return list with one item as on object.
     */
    fun single() = list.single()

    /**
     * Gets all children that are in the widget with id [rootId].
     * @return all children that are in the widget with id [rootId].
     */
    val children
        get() = list.filter {
            it.parent == rootId || (rootId == null && (it.parent == null || it.parent?.isEmpty() == true))
        }.map { Widgets(it.id, list) }

    fun add(widget: WidgetProperties) {

    }

}