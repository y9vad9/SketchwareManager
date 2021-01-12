package io.sketchware.models.sketchware.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SketchwareWidget(
    var name: String,
    var root: List<SketchwareWidgetRoot>
)

@Serializable
class SketchwareImage (
    var rotate: Int = 0,
    var scaleType: String? = null,
    var resName: String? = null
)

@Serializable
class SketchwareLayout (
    var backgroundColor: Int = 0,
    var borderColor: Int = 0,
    var gravity: Int = 0,
    var height: Int = 0,
    var layoutGravity: Int = 0,
    var marginBottom: Int = 0,
    var marginLeft: Int = 0,
    var marginRight: Int = 0,
    var marginTop: Int = 0,
    var orientation: Int = 0,
    var paddingBottom: Int = 0,
    var paddingLeft: Int = 0,
    var paddingRight: Int = 0,
    var paddingTop: Int = 0,
    var weight: Int = 0,
    var weightSum: Int = 0,
    var width: Int = 0
)

@Serializable
class SketchwareText (
    var hint: String? = null,
    var hintColor: Int = 0,
    var imeOption: Int = 0,
    var inputType: Int = 0,
    var line: Int = 0,
    var singleLine: Int = 0,
    var text: String? = null,
    var textColor: Int = 0,
    var textFont: String? = null,
    var textSize: Int = 0,
    var textType: Int = 0
)

@Serializable
class SketchwareWidgetRoot(
    var adSize: String? = null,
    var adUnitId: String? = null,
    var alpha: Double = 0.0,
    var checked: Int = 0,
    var choiceMode: Int = 0,
    var clickable: Int = 0,
    var convert: String? = null,
    var customView: String? = null,
    var dividerHeight: Int = 0,
    var enabled: Int = 0,
    var firstDayOfWeek: Int = 0,
    var id: String? = null,
    var image: SketchwareImage? = null,
    var indeterminate: String? = null,
    var index: Int = 0,
    var inject: String? = null,
    var layout: SketchwareLayout? = null,
    var parent: String? = null,
    var max: Int = 0,
    var parentType: Int = 0,
    var preId: String? = null,
    var preParent: String? = null,
    var preIndex: Int = 0,
    var preParentType: Int = 0,
    var progress: Int = 0,
    var progressStyle: String? = null,
    var scaleX: Double = 0.0,
    var scaleY: Double = 0.0,
    var spinnerMode: Int = 0,
    var text: SketchwareText? = null,
    var translationX: Double = 0.0,
    var translationY: Double = 0.0,
    var type: Int = 0,
)