package `fun`.kotlingang.sketchware.objects.project.content

import `fun`.kotlingang.sketchware.internal.extensions.snakeToUpperCamelCase
import `fun`.kotlingang.sketchware.internal.json.serializers.ActivityOptionSerializer
import io.sketchware.model.project.content.KeyboardSetting
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
    var fileName: String,
    /**
     * File type (Activity / Custom View).
     */
    @Contextual
    var fileType: FileType,
    @Contextual
    var keyboardSetting: KeyboardSetting,
    /**
     * Contains data about enabled components in activity:
     * For example: is drawer enabled, is FAB enabled, etc.
     */
    @Serializable(with = ActivityOptionSerializer::class)
    var options: List<ActivityOption>,
    /**
     * Allowed activity orientation setting.
     */
    @Contextual
    var orientation: Orientation,
    @Contextual
    var theme: ActivityTheme
) {
    /**
     * @return converted [fileName] to java name (foo_bar_foobar -> FooBarFoobarActivity).
     */
    val javaName get() = "${fileName.snakeToUpperCamelCase()}Activity"

    /**
     * @return converted [fileName] to xml name (foo_bar_foobar -> foo_bar_foobar.xml).
     */
    val xmlName get() = "$fileName.xml"

    /**
     * Converts [FileDataModel] to [ActivityView].
     * @throws [IllegalStateException] if [fileType] is not Activity.
     * @return Converted [FileDataModel] to [ActivityView] if it's possible.
     */
    fun toActivityView() = if (fileType == FileType.ACTIVITY)
        ActivityView(fileName, keyboardSetting, options, orientation, theme)
    else throw IllegalStateException("Cannot convert FileDataModel to ActivityView, FileDataModel is not an activity.")

    /**
     * Converts [FileDataModel] to [CustomView].
     * @throws [IllegalStateException] if [fileType] is not Activity.
     * @return Converted [FileDataModel] to [CustomView] if it's possible.
     */
    fun toCustomView() = if (fileType == FileType.ACTIVITY)
        ActivityView(fileName, keyboardSetting, options, orientation, theme)
    else throw IllegalStateException("Cannot convert FileDataModel to ActivityView, FileDataModel is not an custom view.")

    /**
     * Converts [FileDataModel] to [ActivityView].
     * @throws [IllegalStateException] if [fileType] is not Activity.
     * @return Converted [FileDataModel] to [ActivityView] if it's possible.
     */
    fun toDrawerLayout() = if (fileType == FileType.ACTIVITY)
        DrawerView(fileName)
    else throw IllegalStateException("Cannot convert FileDataModel to ActivityView, FileDataModel is not an custom view.")

}