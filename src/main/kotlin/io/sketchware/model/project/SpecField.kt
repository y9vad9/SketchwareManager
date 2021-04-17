package io.sketchware.model.project

import io.sketchware.exception.InvalidMenuArgumentTypeException
import kotlinx.serialization.Serializable

@Serializable
open class SpecField(
    open var text: String
) {

    /**
     * Unites current spec field and [another] in list.
     */
    operator fun plus(another: SpecField): List<SpecField> {
        return listOf(this, another)
    }

    /**
     * [text] is originally formatted of current field.
     */
    override fun toString() = text
}

open class SpecArgument(override var text: String) : SpecField(text) {
    /**
     * Returns name of argument.
     * @return [String] with name of argument.
     */
    open val argumentName: String
        get() = text.split(".")[1]
}

data class StringSpecArgument(override var text: String) : SpecArgument(text)
data class BooleanSpecArgument(override var text: String) : SpecArgument(text)
data class NumberSpecArgument(override var text: String) : SpecArgument(text)

data class MenuSpecArgument(override var text: String) : SpecArgument(text) {

    override val argumentName: String
        get() = text.split(".")[2]

    /**
     * Tells what type of argument is passed in the list when selected (in Sketchware).
     * @throws InvalidMenuArgumentTypeException if argument is custom
     * or not implemented yet.
     */
    var menuArgumentType: MenuArgumentType
        get() = with(menuArgumentTypeName) {
            return enumValues<MenuArgumentType>().firstOrNull {
                it.serialName == this
            } ?: throw InvalidMenuArgumentTypeException(this)
        }
    set(value) {
        menuArgumentTypeName = value.serialName
    }

    /**
     * Returns name of menu's argument type.
     */
    var menuArgumentTypeName: String
        get() = text.split(".")[1]
        set(value) {
            text = text.split(".").toMutableList().apply {
                set(1, value)
            }.joinToString(".")
        }

}

internal fun List<SpecField>.toStringValue() = this.joinToString(" ")