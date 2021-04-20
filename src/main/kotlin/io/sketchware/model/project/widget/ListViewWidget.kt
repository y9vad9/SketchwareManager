package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

class ListViewWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * ListView decorator height in DP.
     */
    var dividerHeight by view::dividerHeight

    /**
     * ListView custom view name.
     */
    var customView by view::customView
}