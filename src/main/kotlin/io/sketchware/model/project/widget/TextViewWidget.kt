package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

open class TextViewWidget internal constructor(
    view: WidgetProperties
) : BaseWidget(view) {

    internal val textProps get() = view.text

    /**
     * TextView content text.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:text">Android docs.</a>
     */
    var text by textProps::text
    /**
     * TextView text color.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:textColor">Android docs.</a>
     */
    var textColor by textProps::textColor
    /**
     * TextView text size in DP.
     */
    var textSize by textProps::textSize
    /**
     * Says how much lines are enabled by default.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:lines">Android docs.</a>
     */
    var lines by textProps::line
    /**
     * Says is text a single line.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:singleLine">Android docs.</a>
     */
    var singleLine by textProps::singleLine
    /**
     * Name of text font in textview.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:fontFamily">Android docs.</a>
     */
    var textFont by textProps::textFont
    /**
     * Text Style (for example: NORMAL, BOLD, ITALIC, BOLD|ITALIC     * @see <a href="https://developer.android.com/reference/android/widget/TextView#attr_android:textStyle">Android docs.</a>
     */
    var textStyle by textProps::textType
}