package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.EnumMenuArgument
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.view.Visibility

/**
 * Argument in blocks which contains map view type.
 * @param selected - menu item which was selected.
 */
class ViewVisibilityMenuArgument(selected: Visibility? = null) : EnumMenuArgument<Visibility>(selected)