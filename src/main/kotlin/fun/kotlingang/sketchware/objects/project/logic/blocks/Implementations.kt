package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.annotations.ExperimentalSWManagerAPI
import `fun`.kotlingang.sketchware.objects.project.logic.BlockProperties
import `fun`.kotlingang.sketchware.objects.project.logic.SpecField
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.Argument

sealed class BlockImpl(properties: BlockProperties) : Block {
    /**
     * Block's color as [Int] value.
     * @return [Int] with color of current block.
     */
    override var color by properties::color

    /**
     * Block's spec with data about block text, arguments, etc.
     * @return [List] with [SpecField] items inside.
     */
    override var spec: List<BlockSpec> by properties::spec

    override val arguments: List<Argument> = TODO()
}

/**
 * Primitive blocks
 */
class BooleanPrimitiveBlockImpl(properties: BlockProperties) : BooleanPrimitiveBlock, BlockImpl(properties)
class NumberPrimitiveBlockImpl(properties: BlockProperties) : NumberPrimitiveBlock, BlockImpl(properties)
class StringPrimitiveBlockImpl(properties: BlockProperties) : StringPrimitiveBlock, BlockImpl(properties)

/**
 * Structured blocks
 */
class MapBlockImpl(properties: BlockProperties) : MapBlock, BlockImpl(properties)
class ListStringBlockImpl(properties: BlockProperties) : ListStringBlock, BlockImpl(properties)
class ListNumberBlockImpl(properties: BlockProperties) : ListNumberBlock, BlockImpl(properties)
class ListMapBlockImpl(properties: BlockProperties) : ListMapBlock, BlockImpl(properties)

/**
 * Operators
 */
open class IfStyledBlockImpl(properties: BlockProperties, private val subStack1: List<Block>) : IfStyledBlock,
    BlockImpl(properties) {
    /**
     * First sub stack with list of blocks inside.
     * @return [List] with [Block] items.
     */
    override val firstSubStack by lazy { subStack1.toMutableList() }
}

class IfElseStyledBlockImpl(properties: BlockProperties, subStack1: List<Block>, private val subStack2: List<Block>) :
    IfElseStyledBlock, IfStyledBlockImpl(properties, subStack1) {
    /**
     * Second sub stack with list of blocks inside.
     * @return [List] with [Block] items.
     */
    override val secondSubStack by lazy { subStack2.toMutableList() }
}

class StopStyledBlockImpl(properties: BlockProperties) : StopStyledBlock, BlockImpl(properties)

/**
 * Components
 */
class BluetoothComponentBlockImpl(properties: BlockProperties) : BluetoothComponentBlock, BlockImpl(properties)
class CalendarComponentBlockImpl(properties: BlockProperties) : CalendarComponentBlock, BlockImpl(properties)
class CameraComponentBlockImpl(properties: BlockProperties) : CameraComponentBlock, BlockImpl(properties)
class DialogComponentBlockImpl(properties: BlockProperties) : DialogComponentBlock, BlockImpl(properties)
class FilePickerComponentBlockImpl(properties: BlockProperties) : FilePickerComponentBlock, BlockImpl(properties)
class FirebaseAuthComponentBlockImpl(properties: BlockProperties) : FirebaseAuthComponentBlock, BlockImpl(properties)
class FirebaseDatabaseComponentBlockImpl(properties: BlockProperties) : FirebaseDatabaseComponentBlock,
    BlockImpl(properties)

class FirebaseStorageComponentBlockImpl(properties: BlockProperties) : FirebaseStorageComponentBlock,
    BlockImpl(properties)

class GyroscopeComponentBlockImpl(properties: BlockProperties) : GyroscopeComponentBlock, BlockImpl(properties)
class IntentComponentBlockImpl(properties: BlockProperties) : IntentComponentBlock, BlockImpl(properties)
class InterstitialAdComponentBlockImpl(properties: BlockProperties) : InterstitialAdComponentBlock,
    BlockImpl(properties)

class LocationManagerComponentBlockImpl(properties: BlockProperties) : LocationManagerComponentBlock,
    BlockImpl(properties)

class MediaPlayerComponentBlockImpl(properties: BlockProperties) : MediaPlayerComponentBlock, BlockImpl(properties)
class ObjectAnimatorComponentBlockImpl(properties: BlockProperties) : ObjectAnimatorComponentBlock,
    BlockImpl(properties)

class RequestNetworkComponentBlockImpl(properties: BlockProperties) : RequestNetworkComponentBlock,
    BlockImpl(properties)

class SharedPreferencesComponentBlockImpl(properties: BlockProperties) : SharedPreferencesComponentBlock,
    BlockImpl(properties)

class SoundPoolComponentBlockImpl(properties: BlockProperties) : SoundPoolComponentBlock, BlockImpl(properties)
class SpeechToTextComponentBlockImpl(properties: BlockProperties) : SpeechToTextComponentBlock, BlockImpl(properties)
class TextToSpeechComponentBlockImpl(properties: BlockProperties) : TextToSpeechComponentBlock, BlockImpl(properties)
class TimerComponentBlockImpl(properties: BlockProperties) : TimerComponentBlock, BlockImpl(properties)
class VibratorComponentBlockImpl(properties: BlockProperties) : VibratorComponentBlock, BlockImpl(properties)

open class DefaultBlockSpecImpl(internal var value: String) : BlockSpec {
    override fun toString(): String {
        return value
    }

    override fun plus(another: BlockSpec) = listOf(this, another)
}

open class StringBlockSpecArgumentImpl(value: String) : StringBlockSpecArgument, DefaultBlockSpecImpl(value) {
    override var isInputOnly: Boolean
        get() = value.split(".").let { it[it.lastIndex] == "inputOnly" }
        @ExperimentalSWManagerAPI
        set(value) {
            this.value = toString()
                .split(".")
                .toMutableList()
                .also {
                    it[it.lastIndex] = if (value) "inputOnly" else ""
                }.filter(String::isNotBlank)
                .joinToString(".")
        }
}

open class NumberBlockSpecArgumentImpl(value: String) : NumberBlockSpecArgument, DefaultBlockSpecImpl(value)
open class BooleanBlockSpecArgumentImpl(value: String) : BooleanBlockSpecArgument, DefaultBlockSpecImpl(value)

open class MenuBlockSpecArgumentImpl(value: String) : MenuBlockSpecArgument, DefaultBlockSpecImpl(value) {
    override val menuName: String by lazy { value.split(".").last() }
}

sealed class DefaultNamedBlockSpecArgumentImpl(value: String) : NamedBlockSpecArgument, DefaultBlockSpecImpl(value) {
    override var name: String
        get() = toString().split(".").last()
        @ExperimentalSWManagerAPI
        set(value) {
            this.value = toString()
                .split(".")
                .toMutableList()
                .also {
                    it[it.lastIndex] = value
                }.joinToString(".")
        }
}

class NamedStringBlockSpecArgumentImpl(value: String) :
    DefaultNamedBlockSpecArgumentImpl(value), NamedStringBlockSpecArgument {
    override var isInputOnly: Boolean
        get() = value.split(".").let { it[it.lastIndex - 1] == "inputOnly" }
        @ExperimentalSWManagerAPI
        set(value) {
            this.value = toString()
                .split(".")
                .toMutableList()
                .also {
                    it[it.lastIndex - 1] = if (value) "inputOnly" else ""
                }.filter(String::isNotBlank)
                .joinToString(".")
        }
}

class NamedNumberBlockSpecArgumentImpl(value: String) : DefaultNamedBlockSpecArgumentImpl(value),
    NamedNumberBlockSpecArgument

class NamedBooleanBlockSpecArgumentImpl(value: String) :
    DefaultNamedBlockSpecArgumentImpl(value), NamedBooleanBlockSpecArgument

class NamedMenuBlockSpecArgumentImpl(value: String) : DefaultNamedBlockSpecArgumentImpl(value),
    NamedMenuBlockSpecArgument {
    override val menuName: String
        get() = toString().split(".").last()
    override var name: String
        get() = value.split(".").let { it[it.lastIndex - 1] }
        @ExperimentalSWManagerAPI
        set(value) {
            this.value = toString()
                .split(".")
                .toMutableList()
                .also {
                    it[it.lastIndex - 1] = value
                }.joinToString(".")
        }
}