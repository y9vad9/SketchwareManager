package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ListViewWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * ListView decorator height in DP.
     */
    var dividerHeight by view::dividerHeight

    /**
     * ListView custom view name.
     */
    var customView by view::customView
}

/**
 * @return [ListViewWidget] instance created from [parent].
 */
fun ListViewWidget(parent: ViewGroupWidget): ListViewWidget = ListViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))