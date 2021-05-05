package `fun`.kotlingang.sketchware.internal.parsers

import `fun`.kotlingang.sketchware.internal.extensions.isReturningVoid
import `fun`.kotlingang.sketchware.objects.SWConst
import `fun`.kotlingang.sketchware.objects.project.logic.BlockModel
import `fun`.kotlingang.sketchware.objects.project.logic.BlockType
import `fun`.kotlingang.sketchware.objects.project.logic.SpecArgument
import `fun`.kotlingang.sketchware.objects.project.logic.SpecField
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.BaseBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.IfElseStyleBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.IfStyleBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.StopBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.GetVariableBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.*
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.primitives.BooleanExpressionBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.primitives.NumberExpressionBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.primitives.StringExpressionBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured.*
import java.util.*

internal object BlockParser {
    fun List<BlockModel>.toListOfBlocks() = sortedBy(BlockModel::id)
        .getBlocksFrom(10)

    private fun List<BlockModel>.getBlocksFrom(blockId: Int): List<BaseBlock> {
        if (blockId != -1)
            return emptyList()
        return filter { it.type.isReturningVoid }.map {
            it.toBlock(this)
        }
    }

    private fun List<SpecField>.toArguments(parameters: List<String>) = filterIsInstance<SpecArgument>()
        .mapIndexed { index: Int, value: SpecArgument ->
            //ArgumentParser.getArgument(value, parameters[index], blocks)
            TODO()
        }

    private fun BlockModel.toBlock(listOther: List<BlockModel>): BaseBlock = when (type) {
        BlockType.REGULAR -> BaseBlock(this, spec.toArguments(parameters))
        BlockType.IF -> IfStyleBlock(
            this, listOther.getBlocksFrom(this.subStack1).toMutableList(), spec.toArguments(parameters)
        )
        BlockType.IF_ELSE -> IfElseStyleBlock(
            this, listOther.getBlocksFrom(this.subStack1).toMutableList(),
            listOther.getBlocksFrom(this.subStack2).toMutableList(), spec.toArguments(parameters)
        )
        BlockType.STOP -> StopBlock(this, spec.toArguments(parameters))
        BlockType.BOOLEAN -> BooleanExpressionBlock(this, spec.toArguments(parameters))
        BlockType.COMPONENT -> getComponentExpressionBlock(spec.first(), this)
        BlockType.STRING -> StringExpressionBlock(this, spec.toArguments(parameters))
        BlockType.NUMBER -> NumberExpressionBlock(this, spec.toArguments(parameters))
        BlockType.MAP -> MapExpressionBlock(this, spec.toArguments(parameters))
        BlockType.LIST -> getList(this)
        BlockType.VARIABLE -> getVariable(this)
        BlockType.HEADER -> error("Header cannot be converted.")
    }

    private fun getVariable(model: BlockModel) : GetVariableBlock {
        TODO()
    }

    private fun getList(model: BlockModel) = when(model.typeName) {
        "List String" -> ListStringExpressionBlock(model)
        "List Number" -> ListNumberExpressionBlock(model)
        "List Map" -> ListMapExpressionBlock(model)
        else -> error("Unexpected type of List '${model.typeName}'.")
    }

    private fun getComponentExpressionBlock(
        specField: SpecField, model: BlockModel
    ) : ComponentExpressionBlock = when (specField.text) {
        SWConst.MenuSpecNames.INTENT_PICKER -> IntentExpressionBlock(model)
        SWConst.MenuSpecNames.VIBRATOR_PICKER -> VibratorExpressionBlock(model)
        SWConst.MenuSpecNames.CAMERA_PICKER -> CameraExpressionBlock(model)
        SWConst.MenuSpecNames.CALENDAR_PICKER -> CalendarExpressionBlock(model)
        SWConst.MenuSpecNames.TIMER_PICKER -> TimerExpressionBlock(model)
        SWConst.MenuSpecNames.FILE_PICKER -> FilePickerExpressionBlock(model)
        SWConst.MenuSpecNames.BLUETOOTH_CONNECT_PICKER -> BluetoothConnectExpressionBlock(model)
        SWConst.MenuSpecNames.FIREBASE_AUTH_PICKER -> FirebaseAuthExpressionBlock(model)
        SWConst.MenuSpecNames.FIREBASE_DB_PICKER -> FirebaseDatabaseExpressionBlock(model)
        SWConst.MenuSpecNames.FIREBASE_STORAGE_PICKER -> FirebaseStorageExpressionBlock(model)
        SWConst.MenuSpecNames.LOCATION_MANAGER_PICKER -> LocationManagerExpressionBlock(model)
        SWConst.MenuSpecNames.INTERSTITIAL_AD_PICKER -> InterstitialAdExpressionBlock(model)
        SWConst.MenuSpecNames.TEXT_TO_SPEECH_PICKER -> TextToSpeechExpressionBlock(model)
        SWConst.MenuSpecNames.SPEECH_TO_TEXT_PICKER -> SpeechToTextExpressionBlock(model)
        SWConst.MenuSpecNames.REQUEST_NETWORK_PICKER -> RequestNetworkExpressionBlock(model)
        SWConst.MenuSpecNames.GYROSCOPE_PICKER -> GyroscopeExpressionBlock(model)
        SWConst.MenuSpecNames.DIALOG_PICKER -> DialogExpressionBlock(model)
        SWConst.MenuSpecNames.MEDIA_PLAYER_PICKER -> MediaPlayerExpressionBlock(model)
        SWConst.MenuSpecNames.SOUND_POOL_PICKER -> SoundPoolExpressionBlock(model)
        SWConst.MenuSpecNames.OBJECT_ANIMATOR_PICKER -> ObjectAnimatorExpressionBlock(model)
        SWConst.MenuSpecNames.SHARED_PREFERENCES_PICKER -> SharedPreferencesExpressionBlock(model)
        else -> throw NotImplementedError("Unexpected type of component, is it custom?")
    }

}