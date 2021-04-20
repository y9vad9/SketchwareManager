package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class SpinnerWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * Spinner view mode.
     */
    var spinnerMode by view::spinnerMode
}