package io.sketchware.models.collections

import io.sketchware.interfaces.CollectionItem
import kotlinx.serialization.Serializable

@Serializable
data class FileCollectionItem(
    /**
     * Name of collection.
     */
    override val name: String,
    /**
     * For this type of collection, it contains name of file.
     */
    override val data: String,
    /**
     * Does not contain anything for this type of collection.
     */
    override val reserved1: String? = null
) : CollectionItem<String, String>