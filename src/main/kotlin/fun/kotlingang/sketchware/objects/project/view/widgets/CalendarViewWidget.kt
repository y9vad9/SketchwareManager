package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class CalendarViewWidget(view: WidgetProperties) : BaseWidget(view) {
    var firstDayOfWeek by view::firstDayOfWeek
}