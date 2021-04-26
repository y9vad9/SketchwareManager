package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class FloatingActionButtonWidget(internal val properties: WidgetProperties = WidgetProperties()) {
    /**
     * Image inside floating action button widget.
     */
    val image by properties.image::resName

    /**
     * Stores data about layout margin at left (Indent outside layout).
     */
    var marginLeft by properties.layout::marginLeft

    /**
     * Stores data about layout margin at right (Indent outside layout).
     */
    var marginRight by properties.layout::marginRight

    /**
     * Stores data about layout margin at top (Indent outside layout).
     */
    var marginTop by properties.layout::marginTop

    /**
     * Stores data about layout margin at bottom (Indent outside layout).
     */
    var marginBottom by properties.layout::marginBottom

    /**
     * Stores data about widget gravity in parent layout.
     */
    var layoutGravity by properties.layout::layoutGravity

}