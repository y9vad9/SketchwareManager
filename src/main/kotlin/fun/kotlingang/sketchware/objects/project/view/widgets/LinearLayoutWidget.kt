package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class LinearLayoutWidget(
    view: WidgetProperties = WidgetProperties(),
    _children: MutableList<BaseWidget> = mutableListOf()
) : ViewGroupWidget(view, _children)
