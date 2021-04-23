package `fun`.kotlingang.sketchware.objects.customs

import kotlinx.serialization.Serializable

@Serializable
data class SWStudioMenu(
    /**
     * String identify of custom menu.
     */
    val name: String,
    /**
     * Title which shows in dialog while choosing option.
     */
    val title: String,
    /**
     * Options to choose.
     */
    val data: List<String>
)