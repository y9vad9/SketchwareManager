package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class ScrollViewWidget(
    view: WidgetProperties,
    _children: MutableList<BaseWidget>
) : ViewGroupWidget(view, _children)