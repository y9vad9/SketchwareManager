package `fun`.kotlingang.sketchware.objects.project.library

import kotlinx.serialization.Serializable

/**
 * Stores name of library and it's information.
 */
@Serializable
data class LibraryModel(
    /**
     * Library unique name (example: admob)
     */
    var name: String,
    /**
     * Library information.
     */
    var information: SketchwareLibraryDataModel
)

