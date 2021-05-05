package `fun`.kotlingang.sketchware.internal.parsers

import `fun`.kotlingang.sketchware.internal.consts.MenuSpecArgumentType
import `fun`.kotlingang.sketchware.internal.consts.MenuSpecArgumentType.*
import `fun`.kotlingang.sketchware.internal.extensions.enumValueOf
import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.ViewArgument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.components.*
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu.*
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.structured.ListMapExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.structured.MapExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.BaseBlock
import kotlin.reflect.KClass

internal object ArgumentParser {
    fun getArgument(argument: MenuSpecArgumentType, parameter: String, blocks: List<BaseBlock>): Argument =
        when (argument) {
            VIEW_PICKER -> ViewArgument(parameter)
            MAP_PICKER -> blocks.getArgumentExpressible(MapExpressibleArgument::class, parameter)
            LIST_MAP_PICKER -> blocks.getArgumentExpressible(ListMapExpressibleArgument::class, parameter)
            COLOR_PICKER -> ColorPickerMenuArgument(parameter)
            INTENT_PICKER -> blocks.getArgumentExpressible(IntentComponentPickerMenuArgument::class, parameter)
            LM_PROVIDER_TYPE_PICKER -> blocks.getArgumentExpressible(ProviderTypeMenuArgument::class, parameter)
            BLUETOOTH_CONNECT_PICKER ->
                blocks.getArgumentExpressible(BluetoothComponentPickerMenuArgument::class, parameter)
            LOCATION_MANAGER_PICKER -> blocks.getArgumentExpressible(LocationManagerComponentPicker::class, parameter)
            REQUEST_TYPE_PICKER -> RequestTypeMenuArgument(enumValueOf(parameter))
            REQUEST_METHOD_PICKER -> RequestMethodMenuArgument(enumValueOf(parameter))
            INTENT_DATA_PICKER -> blocks.getArgumentExpressible(LocationManagerComponentPicker::class, parameter)
            ACTION_PICKER -> IntentActionMenuArgument(enumValueOf(parameter))
            ACTIVITY_PICKER -> ActivityPickerMenuArgument(parameter.nullIfBlank())
            INTENT_FLAG_PICKER -> IntentFlagMenuArgument(enumValueOf(parameter))
            SHARED_PREFERENCES_PICKER ->
                blocks.getArgumentExpressible(SharedPreferencesComponentPickerMenuArgument::class, parameter)
            CALENDAR_PICKER -> blocks.getArgumentExpressible(CalendarComponentPickerMenuArgument::class, parameter)
            CAMERA_PICKER -> blocks.getArgumentExpressible(CameraComponentPickerMenuArgument::class, parameter)
            FILE_PICKER -> blocks.getArgumentExpressible(FilePickerComponentPickerMenuArgument::class, parameter)
            GYROSCOPE_PICKER -> blocks.getArgumentExpressible(GyroscopeComponentPickerMenuArgument::class, parameter)
            FIREBASE_DB_PICKER ->
                blocks.getArgumentExpressible(FirebaseDatabaseComponentPickerMenuArgument::class, parameter)
            FIREBASE_STORAGE_PICKER ->
                blocks.getArgumentExpressible(FirebaseStorageComponentPickerMenuArgument::class, parameter)
            FIREBASE_AUTH_PICKER -> blocks.getArgumentExpressible(FirebaseAuthComponentPickerMenuArgument::class, parameter)
            INTERSTITIAL_AD_PICKER -> blocks.getArgumentExpressible(InterstitialAdComponentPickerMenuArgument::class, parameter)
            TEXT_TO_SPEECH_PICKER -> blocks.getArgumentExpressible(TextToSpeechComponentPickerMenuArgument::class, parameter)
            SPEECH_TO_TEXT_PICKER -> blocks.getArgumentExpressible(SpeechToTextComponentPicker::class, parameter)
            REQUEST_NETWORK_PICKER -> blocks.getArgumentExpressible(RequestNetworkComponentPickerMenuArgument::class, parameter)
            SOUND_PICKER -> SoundMenuArgument(parameter.nullIfBlank())
            SOUND_POOL_PICKER -> blocks.getArgumentExpressible(SoundPoolComponentPickerMenuArgument::class, parameter)
            OBJECT_ANIMATOR_PICKER -> blocks.getArgumentExpressible(ObjectAnimatorComponentPickerMenuArgument::class, parameter)
            VIBRATOR_PICKER -> blocks.getArgumentExpressible(VibratorComponentPickerMenuArgument::class, parameter)
            TIMER_PICKER -> blocks.getArgumentExpressible(TimerComponentPickerMenuArgument::class, parameter)
            DIALOG_PICKER -> blocks.getArgumentExpressible(DialogComponentPickerMenuArgument::class, parameter)
            MEDIA_PLAYER_PICKER -> blocks.getArgumentExpressible(MediaPlayerComponentPicker::class, parameter)
            CALENDAR_FIELD_PICKER -> CalendarFieldMenuArgument(enumValueOf(parameter))
            TARGET_PROPERTY_PICKER -> TargetPropertyMenuArgument(enumValueOf(parameter))
            REPEAT_MODE_PICKER -> RepeatModeMenuArgument(enumValueOf(parameter))
            INTERPOLATOR_PICKER -> InterpolatorMenuArgument(enumValueOf(parameter))
        }

    private fun isBlockInput(parameter: String) = parameter.startsWith("@")

    private inline fun <reified T : BaseBlock> List<BaseBlock>.getBlockByParameter(parameter: String): T {
        return getBlockById((parameter.substringAfter("@", "")
            .takeUnless { it != "" }
            ?: error("Parameter isn't a block")
            ).toInt())
    }

    private inline fun <reified Arg : Argument> List<BaseBlock>.getAsBlockOr(parameter: String, or: () -> Arg): Arg {
        return if (isBlockInput(parameter))
            getBlockByParameter(parameter)
        else or()
    }

    private inline fun <reified T : BaseBlock> List<BaseBlock>.getBlockById(id: Int): T {
        return first { it.model.id == id } as T
    }

    private fun <T : Argument> List<BaseBlock>.getArgumentExpressible(`class`: KClass<T>, parameter: String): T {
        val constructor = `class`.constructors.first { it.parameters.size == 1 }
        return if (!isBlockInput(parameter))
            constructor.call(parameter, null)
        else constructor.call(
            parameter, getBlockById(parameter.substringAfter("@").toInt())
        )
    }

}