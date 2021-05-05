package `fun`.kotlingang.sketchware.objects.customs

import kotlinx.serialization.Serializable

/**
 * Class with custom menu data.
 */
@Serializable
data class BlockInputMenu(
    /**
     * Unique string identification of menu
     */
    val id: String,
    /**
     * EnumMenuArgument selector name (shows in block, example: ViewArgument)
     */
    val name: String
)
