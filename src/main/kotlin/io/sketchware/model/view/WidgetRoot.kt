package io.sketchware.model.view

import kotlinx.serialization.Serializable

@Serializable
data class WidgetRoot(
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
    var id: String? = null,
    var image: Image = Image(),
    var indeterminate: String? = null,
    var index: Int = 0,
    var inject: String? = null,
    var layout: Layout? = null,
    var parent: String? = null,
    var max: Int = 0,
    var parentType: Int = 0,
    var preId: String? = null,
    var preParent: String? = null,
    var preIndex: Int = 0,
    var preParentType: Int = 0,
    var progress: Int = 0,
    var progressStyle: String = "?android:progressBarStyle",
    var scaleX: Double = 0.0,
    var scaleY: Double = 0.0,
    var spinnerMode: Int = 0,
    var text: Text = Text(),
    var translationX: Double = 0.0,
    var translationY: Double = 0.0,
    var type: Int = 0
)