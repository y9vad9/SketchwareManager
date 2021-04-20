package io.sketchware.model.project.content

import io.sketchware.util.internal.snakeToUpperCamelCase
import io.sketchware.util.internal.serializer.ActivityOptionSerializer
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
    @Serializable(with = ActivityOptionSerializer::class)
    val options: List<ActivityOption>,
    /**
     * Allowed activity orientation setting.
     */
    @Contextual
    val orientation: Orientation,
    @Contextual
    val theme: ActivityTheme
) {
    /**
     * @return converted [fileName] to java name (foo_bar_foobar -> FooBarFoobarActivity).
     */
    val javaName get() = "${fileName.snakeToUpperCamelCase()}Activity"

    /**
     * @return converted [fileName] to xml name (foo_bar_foobar -> foo_bar_foobar.xml).
     */
    val xmlName get() = "$fileName.xml"
}