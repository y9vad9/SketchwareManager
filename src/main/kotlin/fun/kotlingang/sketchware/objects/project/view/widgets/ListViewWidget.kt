package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ListViewWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {
    /**
     * ListViewArgument decorator height in DP.
     */
    var dividerHeight by view::dividerHeight

    /**
     * ListViewArgument custom view name.
     */
    var customView by view::customView
}