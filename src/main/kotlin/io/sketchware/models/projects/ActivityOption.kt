package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface
import io.sketchware.utils.SWConst
import kotlinx.serialization.Serializable

@Serializable
enum class ActivityOption(override val id: Int): IdInterface {
    /**
     * The option says that Drawer is present in the activity.
     */
    DRAWER(SWConst.OPTION_ACTIVITY_DRAWER),

    /**
     * The option says that FAB is present in the activity.
     */
    FAB(SWConst.OPTION_ACTIVITY_FAB),

    /**
     * The option says that Status Bar isn't present in the activity.
     */
    FULLSCREEN(SWConst.OPTION_ACTIVITY_FULL_SCREEN),

    /**
     * The option says that Toolbar is present in the activity.
     */
    TOOLBAR(SWConst.OPTION_ACTIVITY_TOOLBAR)
}