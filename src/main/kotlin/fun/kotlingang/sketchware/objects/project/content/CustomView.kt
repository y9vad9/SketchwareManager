package `fun`.kotlingang.sketchware.objects.project.content

import io.sketchware.model.project.content.KeyboardSetting

open class CustomView(
    /**
     * Custom View xml name (without extension).
     */
    val fileName: String
) {
    /**
     * @return converted [fileName] to xml name (foo_bar_foobar -> foo_bar_foobar.xml).
     */
    val xmlName get() = "$fileName.xml"

    /**
     * Converts [CustomView] to [FileDataModel].
     */
    open fun toFileDataModel() = FileDataModel(
        fileName, FileType.CUSTOMVIEW, KeyboardSetting.HIDDEN, emptyList(), Orientation.BOTH, ActivityTheme.NONE
    )

}
