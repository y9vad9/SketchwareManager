package `fun`.kotlingang.sketchware.objects.project.content

import `fun`.kotlingang.sketchware.internal.json.serializers.ActivityOptionSerializer
import io.sketchware.model.project.content.KeyboardSetting
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

data class ActivityView(
    /**
     * Activity / custom view name.
     */
    var fileName: String,
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
     * Converts [ActivityView] to [FileDataModel].
     */
    fun toFileModel() = FileDataModel(fileName, FileType.ACTIVITY, keyboardSetting, options, orientation, theme)
}