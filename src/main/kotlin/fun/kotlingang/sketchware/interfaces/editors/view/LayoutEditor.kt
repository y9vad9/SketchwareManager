package `fun`.kotlingang.sketchware.interfaces.editors.view

import `fun`.kotlingang.sketchware.interfaces.editors.LocalEditor
import `fun`.kotlingang.sketchware.objects.project.view.widgets.RootView

interface LayoutEditor : LocalEditor {
    /**
     * @return [RootView] with widgets inside.
     */
    val root: RootView
}