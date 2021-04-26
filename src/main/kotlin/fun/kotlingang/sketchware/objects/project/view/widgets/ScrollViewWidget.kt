package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ScrollViewWidget(
    view: WidgetProperties = WidgetProperties(),
    _children: MutableList<BaseWidget> = mutableListOf()
) : ViewGroupWidget(view, _children)
