package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.objects.project.logic.arguments.EnumMenuArgument
import `fun`.kotlingang.sketchware.objects.project.logic.menu.components.file.PublicDirectoryType

/**
 * Argument in blocks which contains public directory type.
 * @param selected - menu item which was selected.
 */
class PublicDirectoryTypeMenuArgument(selected: PublicDirectoryType? = null) : EnumMenuArgument<PublicDirectoryType>(selected)