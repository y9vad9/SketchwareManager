package io.sketchware.model.project.logic

import io.sketchware.`interface`.IdInterface
import io.sketchware.model.SWConst

enum class ComponentType(override val id: Int, val serialName: String): IdInterface {
    Intent(SWConst.ComponentType.INTENT,"intent"),
    SharedPreferences(SWConst.ComponentType.SHARED_PREFERENCES,"sharedpreferences"),
    Calendar(SWConst.ComponentType.CALENDAR, "calendar"),
    Vibrator(SWConst.ComponentType.VIBRATOR, "vibrator"),
    Timer(SWConst.ComponentType.TIMER, "timer"),
    Dialog(SWConst.ComponentType.DIALOG, "dialog"),
    MediaPlayer(SWConst.ComponentType.MEDIA_PLAYER, "mediaplayer"),
    SoundPool(SWConst.ComponentType.SOUND_POOL, "soundpool"),
    ObjectAnimator(SWConst.ComponentType.ANIMATOR, "objectanimator"),
    Camera(SWConst.ComponentType.CAMERA, "camera"),
    FilePicker(SWConst.ComponentType.PICKER, "filepicker"),
    Gyroscope(SWConst.ComponentType.GYROSCOPE, "gyroscope"),
    FirebaseDB(SWConst.ComponentType.FIREBASE_DATABASE, "firebase"),
    FirebaseAuth(SWConst.ComponentType.FIREBASE_AUTH, "firebaseauth"),
    FirebaseStorage(SWConst.ComponentType.FIREBASE_STORAGE, "firebasestorage"),
    AdmobInterstitialAd(SWConst.ComponentType.INTERSTITIAL_AD, "admobInterstitialAd"),
    TextToSpeech(SWConst.ComponentType.TEXT_TO_SPEECH, "texttospeech"),
    SpeechToText(SWConst.ComponentType.TEXT_TO_SPEECH, "speechtotext"),
    RequestNetwork(SWConst.ComponentType.REQUEST_NETWORK, "requestnetwork"),
    BluetoothConnect(SWConst.ComponentType.BLUETOOTH_CONNECT, "bluetooth"),
    LocationManager(SWConst.ComponentType.LOCATION_MANAGER, "locationmanager")
}