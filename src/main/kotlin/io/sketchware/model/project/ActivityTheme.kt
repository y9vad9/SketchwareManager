package io.sketchware.model.project

import io.sketchware.`interface`.IdInterface
import io.sketchware.util.SWConst

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