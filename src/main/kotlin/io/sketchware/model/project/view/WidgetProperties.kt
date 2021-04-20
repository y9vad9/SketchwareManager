package io.sketchware.model.project.view

import io.sketchware.util.internal.serialize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WidgetProperties(
    var adSize: String = "",
    var adUnitId: String = "",
    var alpha: Double = 1.0,
    var checked: Int = 0,
    var choiceMode: Int = 0,
    var clickable: Int = 0,
    var convert: String? = null,
    var customView: String = "",
    var dividerHeight: Int = 0,
    var enabled: Int = 0,
    var firstDayOfWeek: Int = 0,
    var id: String = "",
    var image: Image = Image(),
    var indeterminate: String? = null,
    var index: Int = 0,
    var inject: String? = null,
    var layout: Layout = Layout(),
    var parent: String = "",
    var max: Int = 0,
    @SerialName("parentType")
    var parentTypeId: Int = 0,
    var preId: String? = null,
    var preParent: String? = null,
    var preIndex: Int = 0,
    @SerialName("preParentType")
    var preParentTypeId: Int = 0,
    var progress: Int = 0,
    var progressStyle: String = "?android:progressBarStyle",
    var scaleX: Double = 0.0,
    var scaleY: Double = 0.0,
    var spinnerMode: SpinnerMode = SpinnerMode.DROPDOWN,
    var text: Text = Text(),
    var translationX: Double = 0.0,
    var translationY: Double = 0.0,
    @SerialName("type")
    var typeId: Int = 0
) {
    /**
     * Converts [typeId] to sketchware widget type.
     * Note: if it is impossible to convert (for example,
     * if the widget is with Sketchware Pro), it will return [WidgetType.VIEW].
     * @return [WidgetType].
     */
    val type get() = typeId.toString().serialize<WidgetType>()

    /**
     * Converts [parentTypeId] to sketchware widget type.
     * Note: if it is impossible to convert (for example,
     * if the widget is with Sketchware Pro), it will return [WidgetType.VIEW].
     * @return [WidgetType].
     */
    val parentType get() = parentTypeId.toString().serialize<WidgetType>()

    /**
     * Converts [preParentTypeId] to sketchware widget type.
     * Note: if it is impossible to convert (for example,
     * if the widget is with Sketchware Pro), it will return [WidgetType.VIEW].
     * @return [WidgetType].
     */
    val preParentType get() = preParentTypeId.toString().serialize<WidgetType>()

    /**
     * Says is widget can have children.
     * @return [Boolean] - true if can, false if cannot.
     */
    val canHaveChildren
        get() = type == WidgetType.LINEAR_LAYOUT
            || type == WidgetType.VERTICAL_SCROLL || type == WidgetType.VERTICAL_SCROLL

}