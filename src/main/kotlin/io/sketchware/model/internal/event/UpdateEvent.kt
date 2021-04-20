package io.sketchware.model.internal.event

import io.sketchware.model.project.view.WidgetProperties
import io.sketchware.model.project.widget.BaseWidget
import io.sketchware.model.project.widget.ViewGroupWidget

internal sealed class UpdateEvent

internal sealed class WidgetUpdateEvent : UpdateEvent() {
    /**
     * An event that is raised if some property of the widget has been changed.
     * @param widgetProperties - properties of widget
     */
    class PropertyChanged(widgetProperties: WidgetProperties) : WidgetUpdateEvent()
}

internal sealed class ViewGroupUpdateEvent : UpdateEvent() {
    class ChildAddedUpdate(val parent: ViewGroupWidget, widget: BaseWidget) : ViewGroupUpdateEvent()
    class ChildRemovedUpdate(val parent: ViewGroupWidget, widget: BaseWidget) : ViewGroupUpdateEvent()
}

