package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ScrollViewWidget(
    view: WidgetProperties,
    _children: MutableList<BaseWidget>
) : ViewGroupWidget(view, _children)

/**
 * @return [FloatingActionButtonWidget] instance created from [parent].
 */
fun ScrollViewWidget(parent: ViewGroupWidget): ScrollViewWidget = ScrollViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
), mutableListOf())