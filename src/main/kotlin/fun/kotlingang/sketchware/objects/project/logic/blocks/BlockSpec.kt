package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.annotations.ExperimentalSWManagerAPI

sealed interface BlockSpec {

    /**
     * Converts block spec to it's sketchware-typed value.
     */
    override fun toString(): String

    /**
     * Adds this to [another] and makes from it list.
     * @return [List] with block spec.
     */
    operator fun plus(another: BlockSpec): List<BlockSpec>

}

sealed interface BlockSpecArgument : BlockSpec

sealed interface StringBlockSpecArgument : BlockSpecArgument {
    /**
     * Says is String Argument input only.
     * @return [Boolean] with data about it's expressible status.
     */
    var isInputOnly: Boolean
        @ExperimentalSWManagerAPI set
}

sealed interface NumberBlockSpecArgument : BlockSpecArgument

sealed interface BooleanBlockSpecArgument : BlockSpecArgument

sealed interface MenuBlockSpecArgument : BlockSpecArgument {
    /**
     * Returns menu's name.
     */
    val menuName: String
}


sealed interface NamedBlockSpecArgument {
    /**
     * Name of current argument.
     * @return [String] with name of argument.
     */
    var name: String
        @ExperimentalSWManagerAPI set
}

sealed interface NamedNumberBlockSpecArgument : NumberBlockSpecArgument, NamedBlockSpecArgument

sealed interface NamedStringBlockSpecArgument : StringBlockSpecArgument, NamedBlockSpecArgument

sealed interface NamedBooleanBlockSpecArgument : NumberBlockSpecArgument, NamedBlockSpecArgument

sealed interface NamedMenuBlockSpecArgument : MenuBlockSpecArgument, NamedBlockSpecArgument