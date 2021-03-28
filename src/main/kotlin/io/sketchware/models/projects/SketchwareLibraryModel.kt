package io.sketchware.models.projects

import kotlinx.serialization.Serializable

/**
 * Stores name of library and it's information.
 */
@Serializable
data class SketchwareLibraryModel(
    /**
     * Library unique name (example: admob)
     */
    var name: String,
    /**
     * Library information.
     */
    var information: SketchwareLibraryDataModel
)

