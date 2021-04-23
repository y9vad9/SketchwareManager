package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class SpinnerWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * Spinner view mode.
     */
    var spinnerMode by view::spinnerMode
}