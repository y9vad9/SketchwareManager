package io.sketchware.models.projects

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Stores data about activity or custom view.
 */
@Serializable
class SketchwareDataFileModel(
    /**
     * Activity / custom view name.
     */
    val fileName: String,
    /**
     * File type (Activity / Custom View).
     */
    @Contextual
    val fileType: FileType,
    @Contextual
    val keyboardSetting: KeyboardSetting,
    //TODO description and serializer
    val options: Int,
    /**
     * Allowed activity orientation setting.
     */
    @Contextual
    val orientation: Orientation,
    val theme: Int
) {
    val activityName get() = "${fileName.capitalize()}Activity"
}