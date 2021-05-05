package `fun`.kotlingang.sketchware.objects.project.content

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable
import `fun`.kotlingang.sketchware.objects.SWConst

enum class FileType(override val id: Int) : Identifiable {
    ACTIVITY(SWConst.FileType.PROJECT_FILE_TYPE_ACTIVITY),
    CUSTOMVIEW(SWConst.FileType.PROJECT_FILE_TYPE_CUSTOM_VIEW),
    DRAWER(SWConst.FileType.PROJECT_FILE_TYPE_DRAWER)
}