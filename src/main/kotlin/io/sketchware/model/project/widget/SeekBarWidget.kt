package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

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