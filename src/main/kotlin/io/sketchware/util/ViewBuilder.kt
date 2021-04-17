package io.sketchware.util

import io.sketchware.annotation.ExperimentalSWManagerAPI

@ExperimentalSWManagerAPI
class ViewBuilder {
    /**
     * View name (for example: main)
     */
    var name: String = ""

    /**
     * Second view name (for example: layout on fab or custom view.).
     */
    var layoutName: String? = null

    /**
     * Widget builder.
     * Responsible for adding widgets to view.
     */
    var widgetBuilder: WidgetBuilder = WidgetBuilder()

    inline fun widgetBuilder(builder: WidgetBuilder.() -> Unit) = builder(widgetBuilder)
}