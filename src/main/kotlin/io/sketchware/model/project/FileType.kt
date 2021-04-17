package io.sketchware.model.project

import io.sketchware.`interface`.IdInterface
import io.sketchware.util.SWConst

enum class FileType(override val id: Int) : IdInterface {
    Activity(SWConst.PROJECT_FILE_TYPE_ACTIVITY),
    CustomView(SWConst.PROJECT_FILE_TYPE_CUSTOM_VIEW),
    Drawer(SWConst.PROJECT_FILE_TYPE_DRAWER)
}