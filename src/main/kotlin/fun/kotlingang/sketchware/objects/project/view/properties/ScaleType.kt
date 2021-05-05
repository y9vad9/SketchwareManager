package `fun`.kotlingang.sketchware.objects.project.view.properties

import kotlinx.serialization.Serializable

/**
 * ImageViewArgument scale type.
 */
@Serializable
enum class ScaleType {
    FIT_XY, FIT_START, FIT_CENTER, FIT_END, CENTER, CENTER_CROP, CENTER_INSIDE
}