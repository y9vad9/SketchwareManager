package io.sketchware.models.projects

import kotlinx.serialization.Serializable

/**
 * ImageView scale type.
 */
@Serializable
enum class ScaleType {
    FIT_XY, FIT_START, FIT_CENTER, FIT_END, CENTER, CENTER_CROP, CENTER_INSIDE
}