package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.EnumMenuArgument
import `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.mapview.MapType

/**
 * Argument in blocks which contains map view type.
 * @param selected - menu item which was selected.
 */
class MapTypeMenuArgument(selected: MapType? = null) : EnumMenuArgument<MapType>(selected)