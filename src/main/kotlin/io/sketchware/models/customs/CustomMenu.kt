package io.sketchware.models.customs

import kotlinx.serialization.Serializable

@Serializable
data class CustomMenu(
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
