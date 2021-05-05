package `fun`.kotlingang.sketchware.objects.project.logic.arguments

/**
 * Argument in blocks which can contain only selected menu item.
 * @param selected - menu item which was selected.
 */
open class EnumMenuArgument<M : Enum<M>> internal constructor(open var selected: M?) : MenuArgument()