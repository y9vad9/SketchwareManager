package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

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

/**
 * @return [EditTextWidget] instance created from [parent].
 */
fun EditTextWidget(parent: ViewGroupWidget): EditTextWidget = EditTextWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))