package `fun`.kotlingang.sketchware.interfaces.editors.view

import `fun`.kotlingang.sketchware.interfaces.editors.LocalEditor
import `fun`.kotlingang.sketchware.objects.project.view.widgets.FloatingActionButtonWidget

interface ViewEditor : LocalEditor {

    /**
     * @return [LayoutEditor] with base layout (for example: main.xml,
     * it doesn't include layout with fab or drawer)
     */
    val baseLayout: LayoutEditor

    /**
     * @return [FloatingActionButtonWidget] if view is activity and for it implemented fab.
     */
    val fab: FloatingActionButtonWidget?

    /**
     * @return [LayoutEditor] with layout of widgets in drawer or `null` if it does not exist..
     */
    val drawer: LayoutEditor?

}