package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.interfaces.objects.Library

data class AppCompatLibrary(
    override var isEnabled: Boolean
) : Library {
    /**
     * Converts [AppCompatLibrary] to [LibraryDataModel] which originally formatted for sketchware.
     * @return [LibraryDataModel] converted from current instance.
     */
    override fun toLibraryDataModel() = LibraryDataModel(
        listOf(), "", LibraryType.APPCOMPAT, "", "", "", listOf(), isEnabled
    )
}
