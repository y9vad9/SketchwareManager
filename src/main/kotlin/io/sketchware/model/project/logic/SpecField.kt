package io.sketchware.model.project.logic

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

/**
 * Used to generate a list with a SpecField in a more convenient way.
 */
fun buildSpecFields(builder: SpecFieldBuilder.() -> Unit) =
    SpecFieldBuilder(mutableListOf()).apply(builder).list.toList()

class SpecFieldBuilder internal constructor(internal val list: MutableList<SpecField>) {

    /**
     * Adds simple text field.
     * @param text - simple text which will be shown on block (should contain only english letters or space).
     * @throws IllegalArgumentException if [text] is not valid (string should contains only words or space).
     */
    @Throws(IllegalArgumentException::class)
    fun text(text: String) = if (" " in text.validateSpaceable())
        list.addAll(text.split(" ")
            .map { SpecField(it) })
    else list.add(SpecField(text))

    /**
     * Adds string argument to spec.
     * @param variableName - argument name (should contain only english letters).
     * @throws IllegalArgumentException if [text] is not valid (string should contains only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun string(variableName: String) = variableName.validate().let {
        list.add(StringSpecArgument("%s.$it"))
    }

    /**
     * Adds boolean argument to spec.
     * @param variableName - argument name (should contain only english letters).
     * @throws IllegalArgumentException if [text] is not valid (string should contains only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun boolean(variableName: String) = variableName.validate().let {
        list.add(BooleanSpecArgument("%b.$it"))
    }

    /**
     * Adds number (double) argument to spec.
     * @param variableName - argument name (should contain only english letters).
     * @throws IllegalArgumentException if [text] is not valid (string should contains only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun number(variableName: String) = variableName.validate().let {
        list.add(BooleanSpecArgument("%d.$it"))
    }

    /**
     * Adds menu argument to spec.
     * @param variableName - argument name (should contain only english letters).
     * @param type - argument type (for example: intent, shared preference)
     * @throws IllegalArgumentException if [text] is not valid (string should contains only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun menu(variableName: String, type: MenuArgumentType) = variableName.validate().let {
        list.add(MenuSpecArgument("%m.${type.serialName}.$it"))
    }

    /**
     * Adds menu argument to spec.
     * Note: if you need to specify Spec for moreblock, do not specify custom menus here.
     * @param variableName - argument name (should contain only english letters).
     * @param typeName - argument type name (for example: intent, objectanimator)
     * @throws IllegalArgumentException if [text] is not valid (string should contain only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun menu(variableName: String, typeName: String) = variableName.validate().let {
        list.add(MenuSpecArgument("%m.$typeName.$it"))
    }

    /**
     * Adds instance of [SpecField] into list of fields.
     * @param field - spec field to add.
     * @throws IllegalArgumentException if [text] is not valid (string should contain only english letters).
     */
    @Throws(IllegalArgumentException::class)
    fun append(field: SpecField) {
        when (field) {
            is SpecArgument -> field.argumentName.validate()
            else -> field.text.validate()
        }
        list.add(field)
    }

    /**
     * @return [this] if value is valid (string should contain only words or space)
     * or throws [IllegalArgumentException] if field have invalid symbols.
     */
    @Throws(IllegalArgumentException::class)
    private fun String.validateSpaceable(): String =
        if (Regex("[a-zA-Z| ]*").matches(this))
            this
        else throw IllegalArgumentException(
            "Field should contain only from english letters or space, but input is '$this'."
        )

    /**
     * @return [this] if value is valid (string should contain only english words)
     * or throws [IllegalArgumentException] if field have invalid symbols.
     */
    @Throws(IllegalArgumentException::class)
    private fun String.validate(): String =
        if (Regex("[a-zA-Z]*").matches(this))
            this
        else throw IllegalArgumentException(
            "Field should contain only from english letters, but input is '$this'."
        )

}

internal fun List<SpecField>.toStringValue() = this.joinToString(" ")