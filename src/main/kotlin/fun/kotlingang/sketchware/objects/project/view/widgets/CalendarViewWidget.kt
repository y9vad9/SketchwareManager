package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class CalendarViewWidget(view: WidgetProperties) : BaseWidget(view) {
    var firstDayOfWeek by view::firstDayOfWeek
}

/**
 * @return [CalendarViewWidget] instance created from [parent].
 */
fun CalendarViewWidget(parent: ViewGroupWidget): CalendarViewWidget = CalendarViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))