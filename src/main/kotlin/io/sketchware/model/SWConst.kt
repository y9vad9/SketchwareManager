package io.sketchware.model

/**
 * Contains Sketchware Activity Info constants.
 */
internal object SWConst {

    object KeyboardOption {
        const val KEYBOARD_STATE_HIDDEN = 2
        const val KEYBOARD_STATE_UNSPECIFIED = 0
        const val KEYBOARD_STATE_VISIBLE = 1
    }

    object ActivityOption {
        const val OPTION_ACTIVITY_DRAWER = 4
        const val OPTION_ACTIVITY_FAB = 8
        const val OPTION_ACTIVITY_FULL_SCREEN = 2
        const val OPTION_ACTIVITY_MASK = 15
        const val OPTION_ACTIVITY_SHIFT = 0
        const val OPTION_ACTIVITY_TOOLBAR = 1
    }

    object ThemeOrientation {
        const val ORIENTATION_BOTH = 2
        const val ORIENTATION_LANDSCAPE = 1
        const val ORIENTATION_PORTRAIT = 0
    }

    object FileType {
        const val PROJECT_FILE_TYPE_ACTIVITY = 0
        const val PROJECT_FILE_TYPE_CUSTOM_VIEW = 1
        const val PROJECT_FILE_TYPE_DRAWER = 2
    }

    object Theme {
        const val THEME_DEFAULT = 0
        const val THEME_FULL_SCREEN = 2
        const val THEME_NO_ACTIONBAR = 1
        const val THEME_NONE = -1
    }

    object WidgetTypeIds {
        /**
         * Widget type: LinearLayout (V/H)
         */
        const val LINEAR_LAYOUT = 0

        /**
         * Widget type: View (Unknown)
         */
        const val VIEW = 1

        /**
         * Widget type: HorizontalScrollView
         */
        const val HORIZONTAL_SCROLL = 2

        /**
         * Widget type: ScrollView
         */
        const val VERTICAL_SCROLL = 12

        /**
         * Widget type: Button
         */
        const val BUTTON = 3

        /**
         * Widget type: TextView
         */
        const val TEXT_VIEW = 4

        /**
         * Widget type: EditText
         */
        const val EDIT_TEXT = 5

        /**
         * Widget type: ImageView
         */
        const val IMAGE_VIEW = 6

        /**
         * Widget type: WebView
         */
        const val WEBVIEW = 7

        /**
         * Widget type: ProgressBar
         */
        const val PROGRESS_BAR = 8

        /**
         * Widget type: ListView
         */
        const val LISTVIEW = 9

        /**
         * Widget type: Spinner
         */
        const val SPINNER = 10

        /**
         * Widget type: Checkbox
         */
        const val CHECKBOX = 11

        /**
         * Widget type: Switch
         */
        const val SWITCH = 13

        /**
         * Widget type: SeekBar
         */
        const val SEEKBAR = 14

        /**
         * Widget type: CalendarView
         */
        const val CALENDAR = 15

        /**
         * Widget type: AdView
         */
        const val ADVIEW = 17

        /**
         * Widget type: MapView
         */
        const val MAP_VIEW = 18

    }

    object ComponentType {
        const val INTENT = 1
        const val SHARED_PREFERENCES = 2
        const val CALENDAR = 3
        const val VIBRATOR = 4
        const val TIMER = 5
        const val DIALOG = 7
        const val MEDIA_PLAYER = 8
        const val SOUND_POOL = 9
        const val ANIMATOR = 10
        const val GYROSCOPE = 11
        const val CAMERA = 15
        const val PICKER = 16
        const val FIREBASE_DATABASE = 6
        const val FIREBASE_AUTH = 12
        const val FIREBASE_STORAGE = 14
        const val INTERSTITIAL_AD = 13
        const val TEXT_TO_SPEECH = 18
        const val SPEECH_TO_TEXT = 19
        const val REQUEST_NETWORK = 17
        const val BLUETOOTH_CONNECT = 20
        const val LOCATION_MANAGER = 21
    }

}