package io.sketchware.util

import io.sketchware.model.view.WidgetRoot

//TODO Widget Builder
class WidgetBuilder(
    var widgets: MutableList<WidgetRoot> = mutableListOf()
) {

//    fun WidgetRoot.addTextView(
//        id: String,
//        text: String = "TextView",
//        textSize: Int = 12,
//        textFont: String = "default_font",
//        textColor: Int = -16777216
//    ) = addTextView(
//        id, Text(text = text, textSize = textSize, textFont = textFont, textColor = textColor)
//    )
//
//    fun WidgetRoot.addTextViewAfter(widgetId: String, text: Text) =
//        widgets.add(WidgetRoot(
//            text = text, id = widgetId, type = WidgetType.TEXT_VIEW, preId = this.id,
//        ))
//
//    fun addEditText(
//        id: String,
//        text: String = "TextView",
//        textSize: Int = 12,
//        textFont: String = "default_font",
//        textColor: Int = -16777216,
//        hintText: String,
//        hintColor: Int = -10453621
//    ) = addEditText(
//        id, Text(
//            text = text, textSize = textSize, textFont = textFont,
//            textColor = textColor, hint = hintText, hintColor = hintColor
//        )
//    )
//
//    fun addEditText(id: String, text: Text) =
//        widgets.add(WidgetRoot(text = text, id = id, type = WidgetType.EDIT_TEXT))

}