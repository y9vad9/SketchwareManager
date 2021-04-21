package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class AdViewWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * AdView Banner Size.
     */
    var size by view::adViewSize
}