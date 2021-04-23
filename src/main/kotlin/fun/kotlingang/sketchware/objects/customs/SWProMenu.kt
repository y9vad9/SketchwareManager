package `fun`.kotlingang.sketchware.objects.customs

import kotlinx.serialization.Serializable

@Serializable
data class SWProMenu(
    /**
     * String identify of custom menu.
     */
    var id: String,
    /**
     * Name which shows in block.
     */
    var name: String,
    /**
     * Title which shows in dialog while choosing option.
     */
    var title: String,
    /**
     * Options to choose.
     */
    var options: List<String>
) {
    fun toSWStudioMenu() = SWStudioMenu(name, title, options)
}
