package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class AdViewWidget(view: WidgetProperties) : BaseWidget(view) {
    /**
     * AdView Banner Size.
     */
    var size by view::adViewSize
}

/**
 * @return [AdViewWidget] instance created from [parent].
 */
fun AdViewWidget(parent: ViewGroupWidget): AdViewWidget = AdViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))