package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class SpinnerWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * Spinner view mode.
     */
    var spinnerMode by view::spinnerMode
}

/**
 * @return [SpinnerWidget] instance created from [parent].
 */
fun SpinnerWidget(parent: ViewGroupWidget): SpinnerWidget = SpinnerWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))