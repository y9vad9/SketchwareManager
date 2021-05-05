package `fun`.kotlingang.sketchware.interfaces.objects

import `fun`.kotlingang.sketchware.objects.project.library.LibraryDataModel

interface Library {
    /**
     * Says is library enabled.
     */
    var isEnabled: Boolean

    /**
     * Converts [Library] to [LibraryDataModel] which originally formatted for sketchware.
     * @return [LibraryDataModel] converted from current instance.
     */
    fun toLibraryDataModel(): LibraryDataModel

}