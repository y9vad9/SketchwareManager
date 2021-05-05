package `fun`.kotlingang.sketchware.objects

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
         * Widget type: ViewArgument (Unknown)
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
         * Widget type: ButtonArgument
         */
        const val BUTTON = 3

        /**
         * Widget type: TextViewArgument
         */
        const val TEXT_VIEW = 4

        /**
         * Widget type: EditTextArgument
         */
        const val EDIT_TEXT = 5

        /**
         * Widget type: ImageViewArgument
         */
        const val IMAGE_VIEW = 6

        /**
         * Widget type: WebViewArgument
         */
        const val WEBVIEW = 7

        /**
         * Widget type: ProgressBarArgument
         */
        const val PROGRESS_BAR = 8

        /**
         * Widget type: ListViewArgument
         */
        const val LISTVIEW = 9

        /**
         * Widget type: SpinnerArgument
         */
        const val SPINNER = 10

        /**
         * Widget type: Checkbox
         */
        const val CHECKBOX = 11

        /**
         * Widget type: SwitchArgument
         */
        const val SWITCH = 13

        /**
         * Widget type: SeekBarArgument
         */
        const val SEEKBAR = 14

        /**
         * Widget type: CalendarViewArgument
         */
        const val CALENDAR = 15

        /**
         * Widget type: AdViewArgument
         */
        const val ADVIEW = 17

        /**
         * Widget type: MapViewArgument
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

    object MenuSpecNames {

        const val VIEW_PICKER = "view"

        const val MAP_PICKER = "varMap"

        const val LIST_MAP_PICKER = "listMap"

        const val COLOR_PICKER = "color"

        const val INTENT_PICKER = "intent"

        /**
         * Spec: `%m.intent`
         */
        object Intent {
            /**
             * Using for block with spec `%m.intent setData %s.intentData`
             */
            const val DATA_PICKER = "intentData"

            /**
             * Using for block with spec `%m.intent`
             */
            const val ACTION_PICKER = "intentAction"

            /**
             * Using for blocks where argument should receive activity name.
             */
            const val ACTIVITY_PICKER = "activity"

            /**
             * Using for flags picking for intent.
             */
            const val INTENT_FLAG_PICKER = "intentFlags"

        }

        const val SHARED_PREFERENCES_PICKER = "file"

        const val CALENDAR_PICKER = "calendar"

        object Calendar {
            /**
             * Picker for time units such as minutes, hours, etc.
             */
            const val CALENDAR_FIELD_PICKER = "calendarField"
        }

        const val VIBRATOR_PICKER = "vibrator"

        const val TIMER_PICKER = "timer"

        const val DIALOG_PICKER = "dialog"

        const val MEDIA_PLAYER_PICKER = "mediaplayer"

        object MediaPlayer {
            const val SOUND_PICKER = "sound"
        }

        const val SOUND_POOL_PICKER = "soundpool"

        const val OBJECT_ANIMATOR_PICKER = "objectanimator"

        object ObjectAnimator {
            const val TARGET_PROPERTY_PICKER = "animatorproperty"
            const val REPEAT_MODE_PICKER = "aniRepeatMode"
            const val INTERPOLATOR_PICKER = "aniInterpolator"
        }

        const val CAMERA_PICKER = "camera"

        const val FILE_PICKER = "filepicker"

        const val GYROSCOPE_PICKER = "gyroscope"

        const val FIREBASE_DB_PICKER = "firebase"

        const val FIREBASE_STORAGE_PICKER = "firebasestorage"

        const val FIREBASE_AUTH_PICKER = "firebaseauth"

        const val INTERSTITIAL_AD_PICKER = "interstitialad"

        const val TEXT_TO_SPEECH_PICKER = "texttospeech"

        const val SPEECH_TO_TEXT_PICKER = "speechtotext"

        const val REQUEST_NETWORK_PICKER = "requestnetwork"

        object RequestNetwork {
            const val REQUEST_TYPE_PICKER = "requestType"
            const val REQUEST_METHOD_PICKER = "method"
        }

        const val BLUETOOTH_CONNECT_PICKER = "bluetoothconnect"

        const val LOCATION_MANAGER_PICKER = "locationmanager"

        object LocationManager {
            const val PROVIDER_TYPE_PICKER = "providerType"
        }

    }

}