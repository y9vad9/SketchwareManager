package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

open class CheckboxWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {

    /**
     * Stores info about "checked" status of checkbox.
     */
    var isChecked by view::checked

    private val textProps get() = view.text

    /**
     * Name of text font in textview.
     * @see <a href="https://developer.android.com/reference/android/widget/TextViewArgument#attr_android:fontFamily">Android docs.</a>
     */
    var textFont by textProps::textFont

    /**
     * TextProperties Style (for example: NORMAL, BOLD, ITALIC, BOLD|ITALIC)
     * @see <a href="https://developer.android.com/reference/android/widget/TextViewArgument#attr_android:textStyle">Android docs.</a>
     */
    var textStyle by textProps::textType

    /**
     * TextViewArgument content text.
     * @see <a href="https://developer.android.com/reference/android/widget/TextViewArgument#attr_android:text">Android docs.</a>
     */
    var text by textProps::text

    /**
     * TextViewArgument text color.
     * @see <a href="https://developer.android.com/reference/android/widget/TextViewArgument#attr_android:textColor">Android docs.</a>
     */
    var textColor by textProps::textColor

    /**
     * TextViewArgument text size in DP.
     */
    var textSize by textProps::textSize

}