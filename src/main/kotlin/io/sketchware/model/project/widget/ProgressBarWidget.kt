package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class ProgressBarWidget(view: WidgetProperties) : SeekBarWidget(view) {
    var indeterminate by view::indeterminate
    var progressBarStyle by view::progressStyle
}