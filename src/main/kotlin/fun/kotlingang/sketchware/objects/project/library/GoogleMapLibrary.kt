package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.interfaces.objects.Library

data class GoogleMapLibrary(
    var apiKey: String,
    override var isEnabled: Boolean
) : Library {
    /**
     * Converts [GoogleMapLibrary] to [LibraryDataModel] which originally formatted for sketchware.
     * @return [LibraryDataModel] converted from current instance.
     */
    override fun toLibraryDataModel() = LibraryDataModel(
        listOf(), apiKey, LibraryType.GOOGLE_MAP, "", "", "", listOf(), isEnabled
    )
}