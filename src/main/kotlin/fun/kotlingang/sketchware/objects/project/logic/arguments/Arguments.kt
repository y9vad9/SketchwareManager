package `fun`.kotlingang.sketchware.objects.project.logic.arguments

/**
 * The root of all arguments.
 */
sealed interface Argument

/**
 * The root of any non-expressible argument.
 */
sealed interface NonExpressibleArgument : Argument

/**
 * The root of any expressible argument (argument where input can be block).
 */
sealed interface ExpressibleArgument : ReferenceArgument

/**
 * The root of any argument that refers to something (function or variable).
 * For example, refers to widget or variable (by id).
 */
sealed interface ReferenceArgument : Argument

/**
 * The root of any menu that appears in Sketchware.
 * For example, widget id picker.
 */
sealed interface MenuArgument : ReferenceArgument