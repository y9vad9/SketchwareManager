package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class SpinnerWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {
    /**
     * SpinnerArgument view mode.
     */
    var spinnerMode by view::spinnerMode
}