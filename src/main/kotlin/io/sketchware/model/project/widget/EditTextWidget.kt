package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

open class EditTextWidget internal constructor(
    view: WidgetProperties
) : TextViewWidget(view) {
    /**
     * EditText hint text.
     * @see <a href="https://developer.android.com/reference/android/widget/EditText#attr_android:hint">EditText docs.</a>
     */
    var hint by textProps::hint
    /**
     * EditText hint text color.
     * @see <a href="https://developer.android.com/reference/android/widget/TextView#getHintTextColors()">EditText docs.</a>
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