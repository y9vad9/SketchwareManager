package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

open class EditTextWidget internal constructor(
    view: WidgetProperties = WidgetProperties()
) : TextViewWidget(view) {
    /**
     * EditTextArgument hint text.
     * @see <a href="https://developer.android.com/reference/android/widget/EditTextArgument#attr_android:hint">EditTextArgument docs.</a>
     */
    var hint by textProps::hint

    /**
     * EditTextArgument hint text color.
     * @see <a href="https://developer.android.com/reference/android/widget/TextViewArgument#getHintTextColors()">EditTextArgument docs.</a>
     */
    var hintColor by textProps::hintColor

    /**
     * Every text field expects a certain type of text input,
     * such as an email address, phone number, or just plain text.
     *
     * Indicates the type of content for input.
     * @see <a href="https://developer.android.com/training/keyboard-input/style">Android docs.</a>
     */
    var imeOption by textProps::imeOption

    /**
     * Every text field expects a certain type of text input,
     * such as an email address, phone number, or just plain text.
     *
     * Indicates the type of content for input.
     * @see <a href="https://developer.android.com/training/keyboard-input/style">Android docs.</a>
     */
    var inputType by textProps::inputType
}