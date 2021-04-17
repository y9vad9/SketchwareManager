package io.sketchware.model.project.view

import io.sketchware.util.SWConst

enum class WidgetType(val id: Int) {
    LINEAR_LAYOUT(SWConst.WidgetType.LINEAR_LAYOUT),
    HORIZONTAL_SCROLL(SWConst.WidgetType.HORIZONTAL_SCROLL),
    VERTICAL_SCROLL(SWConst.WidgetType.VERTICAL_SCROLL),
    BUTTON(SWConst.WidgetType.BUTTON),
    TEXTVIEW(SWConst.WidgetType.TEXT_VIEW),
    EDITTEXT(SWConst.WidgetType.EDIT_TEXT),
    IMAGEVIEW(SWConst.WidgetType.IMAGE_VIEW),
    WEBVIEW(SWConst.WidgetType.WEBVIEW),
    PROGRESSBAR(SWConst.WidgetType.PROGRESS_BAR),
    LISTVIEW(SWConst.WidgetType.LISTVIEW),
    SPINNER(SWConst.WidgetType.SPINNER),
    CHECKBOX(SWConst.WidgetType.CHECKBOX),
    SWITCH(SWConst.WidgetType.SWITCH),
    SEEKBAR(SWConst.WidgetType.SEEKBAR),
    CALENDAR(SWConst.WidgetType.CALENDAR),
    ADVIEW(SWConst.WidgetType.ADVIEW),
    MAPVIEW(SWConst.WidgetType.MAP_VIEW)
}