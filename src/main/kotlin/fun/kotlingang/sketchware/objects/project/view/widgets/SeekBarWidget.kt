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