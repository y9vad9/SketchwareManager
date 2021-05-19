package `fun`.kotlingang.sketchware.internal.consts

internal enum class MenuSpecArgumentType(val serialName: String) {
    VIEW_PICKER("view"),
    MAP_PICKER("varMap"),
    LIST_MAP_PICKER("listMap"),
    COLOR_PICKER("color"),
    INTENT_PICKER("intent"),
    LM_PROVIDER_TYPE_PICKER("providerType"),
    BLUETOOTH_CONNECT_PICKER("bluetoothconnect"),
    LOCATION_MANAGER_PICKER("locationmanager"),
    REQUEST_TYPE_PICKER("requestType"),
    REQUEST_METHOD_PICKER("method"),

    SOUND_PICKER("sound"),

    SOUND_POOL_PICKER("soundpool"),

    OBJECT_ANIMATOR_PICKER("objectanimator"),

    VIBRATOR_PICKER("vibrator"),

    TIMER_PICKER("timer"),

    DIALOG_PICKER("dialog"),

    MEDIA_PLAYER_PICKER("mediaplayer"),

    /**
     * Picker for time units such as minutes, hours, etc.
     */
    CALENDAR_FIELD_PICKER("calendarField"),

    /**
     * Using for block with spec `%m.intent setData %s.intentData`
     */
    INTENT_DATA_PICKER("intentData"),

    /**
     * Using for block with spec `%m.intent`
     */
    ACTION_PICKER("intentAction"),

    /**
     * Using for blocks where argument should receive activity name.
     */
    ACTIVITY_PICKER("activity"),

    TARGET_PROPERTY_PICKER("animatorproperty"),
    REPEAT_MODE_PICKER("aniRepeatMode"),
    INTERPOLATOR_PICKER("aniInterpolator"),


    /**
     * Using for flags picking for intent.
     */
    INTENT_FLAG_PICKER("intentFlags"),
    SHARED_PREFERENCES_PICKER("file"),
    CALENDAR_PICKER("calendar"),
    CAMERA_PICKER("camera"),
    FILE_PICKER("filepicker"),
    GYROSCOPE_PICKER("gyroscope"),
    FIREBASE_DB_PICKER("firebase"),
    FIREBASE_STORAGE_PICKER("firebasestorage"),
    FIREBASE_AUTH_PICKER("firebaseauth"),
    INTERSTITIAL_AD_PICKER("interstitialad"),
    TEXT_TO_SPEECH_PICKER("texttospeech"),
    SPEECH_TO_TEXT_PICKER("speechtotext"),
    REQUEST_NETWORK_PICKER("requestnetwork");

    companion object {
        fun of(input: String): MenuSpecArgumentType? {
            return values().firstOrNull { it.serialName == input }
        }
    }
}