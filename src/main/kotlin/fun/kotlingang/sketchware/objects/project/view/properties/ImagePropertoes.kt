package `fun`.kotlingang.sketchware.objects.project.view.properties

import kotlinx.serialization.Serializable

@Serializable
data class ImagePropertoes(
    var rotate: Int = 0,
    var scaleType: ScaleType = ScaleType.CENTER,
    var resName: String = "default_image"
)