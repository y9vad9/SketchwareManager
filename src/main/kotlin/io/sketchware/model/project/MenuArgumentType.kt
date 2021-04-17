package io.sketchware.model.project

enum class MenuArgumentType(val serialName: String) {
    Intent("intent"),
    SharedPreferences("sharedpreferences"),
    Calendar("calendar"),
    Vibrator("vibrator"),
    Timer("timer"),
    Dialog("dialog"),
    MediaPlayer("mediaplayer"),
    SoundPool("soundpool"),
    ObjectAnimator("objectanimator"),
    Camera("camera"),
    FilePicker("picker"),
    Gyroscope("gyroscope"),
    FirebaseDB("firebase"),
    FirebaseAuth("firebaseauth"),
    FirebaseStorage("firebasestorage"),

    //TODO right serial name
    AdmobInterstitialAd("admobInterstitialAd"),
    TextToSpeech("texttospeech"),
    SpeechToText("speechtotext"),
    RequestNetwork("requestnetwork"),

    //TODO right serial name
    BluetoothConnect("bluetooth"),
    LocationManager("locationmanager")
}