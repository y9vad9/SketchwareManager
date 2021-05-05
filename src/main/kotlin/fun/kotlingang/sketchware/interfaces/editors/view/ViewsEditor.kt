package `fun`.kotlingang.sketchware.interfaces.editors.view

import `fun`.kotlingang.sketchware.interfaces.editors.Editor

interface ViewsEditor : Editor {
    /**
     * Gets view by [name].
     * @param name - name of layout (for example: main).
     */
    fun getView(name: String): ViewEditor
}