package io.sketchware.model.project.view

import io.sketchware.util.internal.serializer.IntToBooleanSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Text(
    var hint: String = "",
    var hintColor: Int = -10453621,
    var imeOption: Int = 0,
    var inputType: Int = 0,
    var line: Int = 0,
    @Serializable(with = IntToBooleanSerializer::class)
    var singleLine: Boolean = false,
    var text: String = "",
    var textColor: Int = -16777216,
    var textFont: String = "default_font",
    var textSize: Int = 12,
    @Contextual
    var textType: TextStyle = TextStyle.NORMAL
)