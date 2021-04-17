package io.sketchware.model.project.content

import io.sketchware.`interface`.IdInterface
import io.sketchware.model.SWConst

enum class FileType(override val id: Int) : IdInterface {
    ACTIVITY(SWConst.FileType.PROJECT_FILE_TYPE_ACTIVITY),
    CUSTOMVIEW(SWConst.FileType.PROJECT_FILE_TYPE_CUSTOM_VIEW),
    DRAWER(SWConst.FileType.PROJECT_FILE_TYPE_DRAWER)
}