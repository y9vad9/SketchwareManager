package `fun`.kotlingang.sketchware.objects.project.view.properties

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class LayoutProperties(
    var backgroundColor: Int = 0,
    var borderColor: Int = 0,
    var gravity: Int = 0,
    var height: Int = 0,
    var layoutGravity: Int = 0,
    var marginBottom: Int = 0,
    var marginLeft: Int = 0,
    var marginRight: Int = 0,
    var marginTop: Int = 0,
    @Contextual
    var orientation: LayoutOrientation = LayoutOrientation.HORIZONTAL,
    var paddingBottom: Int = 0,
    var paddingLeft: Int = 0,
    var paddingRight: Int = 0,
    var paddingTop: Int = 0,
    var weight: Int = 0,
    var weightSum: Int = 0,
    var width: Int = 0
)