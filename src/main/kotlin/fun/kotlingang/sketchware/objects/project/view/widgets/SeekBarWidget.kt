package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

open class SeekBarWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * Stores data about max progress value of seekbar.
     */
    var maxProgress by view::max

    /**
     * Stores seekbar progress.
     */
    var progress by view::progress
}

/**
 * @return [SeekBarWidget] instance created from [parent].
 */
fun SeekBarWidget(parent: ViewGroupWidget): SeekBarWidget = SeekBarWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))