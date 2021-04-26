package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ListViewWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {
    /**
     * ListView decorator height in DP.
     */
    var dividerHeight by view::dividerHeight

    /**
     * ListView custom view name.
     */
    var customView by view::customView
}