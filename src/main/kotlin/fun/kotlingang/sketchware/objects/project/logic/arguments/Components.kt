package `fun`.kotlingang.sketchware.objects.project.logic.arguments

import `fun`.kotlingang.sketchware.interfaces.objects.MutableValueContainer
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.*

sealed interface ComponentArgument : Argument

sealed interface ComponentBlockArgument : ComponentArgument, ExpressibleArgument

sealed interface ComponentVariableNameArgument : ComponentArgument, NonExpressibleArgument, ExpressibleArgument

sealed interface BluetoothComponentArgument : ComponentArgument

class BluetoothComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class BluetoothComponentBlockContainer(override var value: BluetoothComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<BluetoothComponentBlock>

sealed interface CalendarComponentArgument : ComponentArgument

class CalendarComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class CalendarComponentBlockContainer(override var value: CalendarComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<CalendarComponentBlock>

sealed interface CameraComponentArgument : ComponentArgument

class CameraComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class CameraComponentBlockContainer(override var value: CameraComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<CameraComponentBlock>

sealed interface DialogComponentArgument : ComponentArgument

class DialogComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class DialogComponentBlockContainer(override var value: DialogComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<DialogComponentBlock>

sealed interface FilePickerComponentArgument : ComponentArgument

class FilePickerComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class FilePickerComponentBlockContainer(override var value: FilePickerComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<FilePickerComponentBlock>

sealed interface FirebaseAuthComponentArgument : ComponentArgument

class FirebaseAuthComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class FirebaseAuthComponentBlockContainer(override var value: FirebaseAuthComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<FirebaseAuthComponentBlock>

sealed interface FirebaseDatabaseComponentArgument : ComponentArgument

class FirebaseDatabaseComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class FirebaseDatabaseComponentBlockContainer(override var value: FirebaseDatabaseComponentBlock?) :
    ComponentBlockArgument,
    MutableValueContainer<FirebaseDatabaseComponentBlock>

sealed interface FirebaseStorageComponentArgument : ComponentArgument

class FirebaseStorageComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class FirebaseStorageComponentBlockContainer(override var value: FirebaseStorageComponentBlock?) :
    ComponentBlockArgument,
    MutableValueContainer<FirebaseStorageComponentBlock>

sealed interface GyroscopeComponentArgument : ComponentArgument

class GyroscopeComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class GyroscopeComponentBlockContainer(override var value: GyroscopeComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<GyroscopeComponentBlock>

sealed interface IntentComponentArgument : ComponentArgument

class IntentComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class IntentComponentBlockContainer(override var value: IntentComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<IntentComponentBlock>

sealed interface InterstitialAdComponentArgument : ComponentArgument

class InterstitialAdComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class InterstitialAdComponentBlockContainer(override var value: InterstitialAdComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<InterstitialAdComponentBlock>

sealed interface LocationManagerComponentArgument : ComponentArgument

class LocationManagerComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class LocationManagerComponentBlockContainer(override var value: LocationManagerComponentBlock?) :
    ComponentBlockArgument,
    MutableValueContainer<LocationManagerComponentBlock>

sealed interface MediaPlayerComponentArgument : ComponentArgument

class MediaPlayerComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class MediaPlayerComponentBlockContainer(override var value: MediaPlayerComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<MediaPlayerComponentBlock>

sealed interface ObjectAnimatorComponentArgument : ComponentArgument

class ObjectAnimatorComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class ObjectAnimatorComponentBlockContainer(override var value: ObjectAnimatorComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<ObjectAnimatorComponentBlock>

sealed interface RequestNetworkComponentArgument : ComponentArgument

class RequestNetworkComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class RequestNetworkComponentBlockContainer(override var value: RequestNetworkComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<RequestNetworkComponentBlock>

sealed interface SharedPreferencesComponentArgument : ComponentArgument

class SharedPreferencesComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class SharedPreferencesComponentBlockContainer(override var value: SharedPreferencesComponentBlock?) :
    ComponentBlockArgument,
    MutableValueContainer<SharedPreferencesComponentBlock>

sealed interface SoundPoolComponentArgument : ComponentArgument

class SoundPoolComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class SoundPoolComponentBlockContainer(override var value: SoundPoolComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<SoundPoolComponentBlock>

sealed interface SpeechToTextComponentArgument : ComponentArgument

class SpeechToTextComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class SpeechToTextComponentBlockContainer(override var value: SpeechToTextComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<SpeechToTextComponentBlock>

sealed interface TextToSpeechComponentArgument : ComponentArgument

class TextToSpeechComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class TextToSpeechComponentBlockContainer(override var value: TextToSpeechComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<TextToSpeechComponentBlock>

sealed interface TimerComponentArgument : ComponentArgument

class TimerComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class TimerComponentBlockContainer(override var value: TimerComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<TimerComponentBlock>

sealed interface VibratorComponentArgument : ComponentArgument

class VibratorComponentNameContainer(override var value: String?) : ComponentVariableNameArgument,
    MutableValueContainer<String>

class VibratorComponentBlockContainer(override var value: TimerComponentBlock?) : ComponentBlockArgument,
    MutableValueContainer<TimerComponentBlock>







