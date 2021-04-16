package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface
import io.sketchware.utils.SWConst

enum class ActivityTheme(override val id: Int) : IdInterface {
    /**
     * Says that activity don't have any special theme.
     */
    NONE(SWConst.THEME_NONE),

    /**
     * Says that activity has default theme.
     */
    DEFAULT(SWConst.THEME_DEFAULT),

    /**
     * Says that activity has no action bar.
     */
    NO_ACTIONBAR(SWConst.THEME_NO_ACTIONBAR),

    /**
     * Says that activity has fullscreen theme.
     */
    FULLSCREEN(SWConst.THEME_FULL_SCREEN)
}