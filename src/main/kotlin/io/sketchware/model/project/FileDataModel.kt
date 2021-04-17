package io.sketchware.model.project

import io.sketchware.util.internal.snakeToUpperCamelCase
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Stores data about activity or custom view.
 */
@Serializable
class FileDataModel(
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
    val options: Int,
    /**
     * Allowed activity orientation setting.
     */
    @Contextual
    val orientation: Orientation,
    @Contextual
    val theme: ActivityTheme
) {
    val activityName get() = "${fileName.snakeToUpperCamelCase()}Activity"
}