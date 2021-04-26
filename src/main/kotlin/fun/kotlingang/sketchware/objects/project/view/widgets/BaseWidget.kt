package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

open class BaseWidget internal constructor(
    internal val view: WidgetProperties = WidgetProperties()
) {

    /**
     * Unique widget string identify.
     */
    var id by view::id

    /**
     * @return view layout for faster access.
     */
    internal val layout get() = view.layout

    /**
     * Stores widget background color.
     */
    var backgroundColor by layout::backgroundColor

    /**
     * Stores data about layout padding at left (Indent inside layout).
     */
    var paddingLeft by layout::paddingLeft

    /**
     * Stores data about layout padding at right (Indent inside layout).
     */
    var paddingRight by layout::paddingRight

    /**
     * Stores data about layout padding at top (Indent inside layout).
     */
    var paddingTop by layout::paddingTop

    /**
     * Stores data about layout padding at bottom (Indent inside layout).
     */
    var paddingBottom by layout::paddingBottom

    /**
     * Stores data about widget layout width.
     */
    var width by layout::width

    /**
     * Stores data about widget layout height.
     */
    var height by layout::height

    /**
     * Stores data about widget layout weight.
     */
    var weight by layout::weight

    /**
     * Stores data about widget layout weightSum.
     */
    var weightSum by layout::weightSum

    /**
     * Stores data about layout margin at left (Indent outside layout).
     */
    var marginLeft by layout::marginLeft

    /**
     * Stores data about layout margin at right (Indent outside layout).
     */
    var marginRight by layout::marginRight

    /**
     * Stores data about layout margin at top (Indent outside layout).
     */
    var marginTop by layout::marginTop

    /**
     * Stores data about layout margin at bottom (Indent outside layout).
     */
    var marginBottom by layout::marginBottom

    /**
     * Stores data about widget gravity in parent layout.
     */
    var layoutGravity by layout::layoutGravity

}