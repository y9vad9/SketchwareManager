package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

/**
 * MenuArgument with color.
 * @param color - int representation of hex or if it is transparent "Color.TRANSPARENT".
 */
open class ColorPickerMenuArgument(color: String? = null) : MenuArgumentString(color)