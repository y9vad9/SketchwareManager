package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class CalendarViewWidget(view: WidgetProperties) : BaseWidget(view) {
    var firstDayOfWeek by view::firstDayOfWeek
}