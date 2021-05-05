package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class AdViewWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {
    /**
     * AdViewArgument Banner Size.
     */
    var size by view::adViewSize
}