package io.sketchware.collections.models

import kotlinx.serialization.Serializable

@Serializable
data class BlockBean(
    /**
     * Contains name of the item
     */
    val name: String,
    /**
     * Contains data about item. In collections it's a file name.
     */
    val data: String,
    /**
     * Not null only if it moreblock
     */
    val reserved1: String? = null
)
