package `fun`.kotlingang.sketchware.interfaces.editors

interface Editor {

    /**
     * Updates data in Editor.
     */
    suspend fun fetch()

    /**
     * Saves data which was edited.
     */
    suspend fun save()
}