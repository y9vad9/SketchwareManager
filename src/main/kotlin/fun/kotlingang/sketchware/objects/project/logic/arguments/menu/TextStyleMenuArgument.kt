package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.EnumMenuArgument
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.textview.TextStyle

/**
 * Argument in blocks which contains text style (normal, bold, italic, bold|italic).
 * @param selected - menu item which was selected.
 */
class TextStyleMenuArgument(selected: TextStyle? = null) : EnumMenuArgument<TextStyle>(selected)