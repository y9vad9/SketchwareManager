package `fun`.kotlingang.sketchware.interfaces.editors

interface LocalEditor {
    /**
     * Restores data after [remove].
     * @return [Boolean] - true if successfully restored or false it there was some error (for example,
     * if it wasn't removed).
     */
    fun restore(): Boolean

    /**
     * Removes data locally.
     * @return [Boolean] - true if successfully removed, or false it there was some error.
     */
    fun remove(): Boolean
}