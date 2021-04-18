package io.sketchware.model.project.view

import io.sketchware.`interface`.IdInterface
import io.sketchware.model.SWConst

enum class WidgetType(override val id: Int, val serialName: String? = null) : IdInterface {
    LINEAR_LAYOUT(SWConst.WidgetType.LINEAR_LAYOUT),
    VIEW(SWConst.WidgetType.VIEW, "view"),
    HORIZONTAL_SCROLL(SWConst.WidgetType.HORIZONTAL_SCROLL),
    VERTICAL_SCROLL(SWConst.WidgetType.VERTICAL_SCROLL),
    BUTTON(SWConst.WidgetType.BUTTON),
    TEXTVIEW(SWConst.WidgetType.TEXT_VIEW, "textview"),
    EDITTEXT(SWConst.WidgetType.EDIT_TEXT),
    IMAGEVIEW(SWConst.WidgetType.IMAGE_VIEW, "imageview"),
    WEBVIEW(SWConst.WidgetType.WEBVIEW, "webview"),
    PROGRESSBAR(SWConst.WidgetType.PROGRESS_BAR, "progressbar"),
    LISTVIEW(SWConst.WidgetType.LISTVIEW, "listview"),
    SPINNER(SWConst.WidgetType.SPINNER, "spinner"),
    CHECKBOX(SWConst.WidgetType.CHECKBOX, "checkbox"),
    SWITCH(SWConst.WidgetType.SWITCH, "switch"),
    SEEKBAR(SWConst.WidgetType.SEEKBAR, "seekbar"),
    CALENDAR(SWConst.WidgetType.CALENDAR, "calendarview"),
    ADVIEW(SWConst.WidgetType.ADVIEW),
    MAPVIEW(SWConst.WidgetType.MAP_VIEW)
}