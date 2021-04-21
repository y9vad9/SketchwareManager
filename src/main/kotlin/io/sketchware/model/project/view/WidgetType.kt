package io.sketchware.model.project.view

import io.sketchware.`interface`.IdInterface
import io.sketchware.model.SWConst

enum class WidgetType(override val id: Int, val serialName: String? = null) : IdInterface {
    LINEAR_LAYOUT(SWConst.WidgetTypeIds.LINEAR_LAYOUT),
    VIEW(SWConst.WidgetTypeIds.VIEW, "view"),
    HORIZONTAL_SCROLL(SWConst.WidgetTypeIds.HORIZONTAL_SCROLL),
    VERTICAL_SCROLL(SWConst.WidgetTypeIds.VERTICAL_SCROLL),
    BUTTON(SWConst.WidgetTypeIds.BUTTON),
    TEXTVIEW(SWConst.WidgetTypeIds.TEXT_VIEW, "textview"),
    EDITTEXT(SWConst.WidgetTypeIds.EDIT_TEXT),
    IMAGEVIEW(SWConst.WidgetTypeIds.IMAGE_VIEW, "imageview"),
    WEBVIEW(SWConst.WidgetTypeIds.WEBVIEW, "webview"),
    PROGRESSBAR(SWConst.WidgetTypeIds.PROGRESS_BAR, "progressbar"),
    LISTVIEW(SWConst.WidgetTypeIds.LISTVIEW, "listview"),
    SPINNER(SWConst.WidgetTypeIds.SPINNER, "spinner"),
    CHECKBOX(SWConst.WidgetTypeIds.CHECKBOX, "checkbox"),
    SWITCH(SWConst.WidgetTypeIds.SWITCH, "switch"),
    SEEKBAR(SWConst.WidgetTypeIds.SEEKBAR, "seekbar"),
    CALENDAR(SWConst.WidgetTypeIds.CALENDAR, "calendarview"),
    ADVIEW(SWConst.WidgetTypeIds.ADVIEW),
    MAPVIEW(SWConst.WidgetTypeIds.MAP_VIEW)
}