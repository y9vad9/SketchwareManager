package io.sketchware.model.custom

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
     * Menu selector name (shows in block, example: View)
     */
    val name: String
)
