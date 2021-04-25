package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class MapViewWidget(view: WidgetProperties) : BaseWidget(view)

fun MapViewWidget(parent: ViewGroupWidget): MapViewWidget = MapViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))