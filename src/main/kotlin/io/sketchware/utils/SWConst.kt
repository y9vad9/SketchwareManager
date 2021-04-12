package io.sketchware.utils

/**
 * Contains Sketchware Activity Info constants.
 */
internal object SWConst {

    //Keyboard Options
    const val KEYBOARD_STATE_HIDDEN = 2
    const val KEYBOARD_STATE_UNSPECIFIED = 0
    const val KEYBOARD_STATE_VISIBLE = 1

    //Theme Features
    const val OPTION_ACTIVITY_DRAWER = 4
    const val OPTION_ACTIVITY_FAB = 8
    const val OPTION_ACTIVITY_FULLSCREEN = 2
    const val OPTION_ACTIVITY_MASK = 15
    const val OPTION_ACTIVITY_SHIFT = 0
    const val OPTION_ACTIVITY_TOOLBAR = 1

    //Theme Orientation
    const val ORIENTATION_BOTH = 2
    const val ORIENTATION_LANDSCAPE = 1
    const val ORIENTATION_PORTRAIT = 0

    //Project File Type
    const val PROJECT_FILE_TYPE_ACTIVITY = 0
    const val PROJECT_FILE_TYPE_CUSTOM_VIEW = 1
    const val PROJECT_FILE_TYPE_DRAWER = 2

    //Theme Styles
    const val THEME_DEFAULT = 0
    const val THEME_FULLSCREEN = 2
    const val THEME_NO_ACTIONBAR = 1
    const val THEME_NONE = -1
}