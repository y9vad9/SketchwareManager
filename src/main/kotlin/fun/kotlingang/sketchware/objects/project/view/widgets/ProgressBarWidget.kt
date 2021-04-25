package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ProgressBarWidget(view: WidgetProperties) : SeekBarWidget(view) {
    var indeterminate by view::indeterminate
    var progressBarStyle by view::progressStyle
}

/**
 * @return [ProgressBarWidget] instance created from [parent].
 */
fun ProgressBarWidget(parent: ViewGroupWidget): ProgressBarWidget = ProgressBarWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))