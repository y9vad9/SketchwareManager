package io.sketchware.model.project.widget

class RootView(
    private val mChildren: MutableList<BaseWidget>
) {
    /**
     * Widget children widgets.
     * @return [MutableList] with [BaseWidget] items.
     */
    val children get() = mChildren
}