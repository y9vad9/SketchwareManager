package io.sketchware.models.projects

import io.sketchware.utils.internal.snakeToUpperCamelCase
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
    /**
     * Contains data about enabled components in activity:
     * For example: is drawer enabled, is FAB enabled, etc.
     */
    @Contextual
    val options: List<ActivityOption>,
    /**
     * Allowed activity orientation setting.
     */
    @Contextual
    val orientation: Orientation,
    @Contextual
    val theme: ActivityTheme
) {
    val activityName get() = "${fileName.capitalize()}Activity"
}