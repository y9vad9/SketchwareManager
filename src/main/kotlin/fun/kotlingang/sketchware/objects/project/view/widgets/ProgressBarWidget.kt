package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ProgressBarWidget(view: WidgetProperties) : SeekBarWidget(view) {
    var indeterminate by view::indeterminate
    var progressBarStyle by view::progressStyle
}