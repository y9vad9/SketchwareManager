package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class LinearLayoutWidget(
    view: WidgetProperties,
    _children: MutableList<BaseWidget>
) : ViewGroupWidget(view, _children)

/**
 * @return [FloatingActionButtonWidget] instance created from [parent].
 */
fun LinearLayoutWidget(parent: ViewGroupWidget): LinearLayoutWidget = LinearLayoutWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
), mutableListOf())