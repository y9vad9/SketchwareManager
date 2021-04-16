package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface
import io.sketchware.utils.SWConst

enum class FileType(override val id: Int) : IdInterface {
    Activity(SWConst.PROJECT_FILE_TYPE_ACTIVITY),
    CustomView(SWConst.PROJECT_FILE_TYPE_CUSTOM_VIEW),
    Drawer(SWConst.PROJECT_FILE_TYPE_DRAWER)
}