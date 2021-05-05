package `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu

import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank

/**
 * Activity picker.
 */
open class ActivityPickerMenuArgument(activity: String? = null) : MenuArgumentString(activity.nullIfBlank())