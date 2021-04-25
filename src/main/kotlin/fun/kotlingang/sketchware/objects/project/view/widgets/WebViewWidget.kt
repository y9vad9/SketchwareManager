package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class WebViewWidget(view: WidgetProperties) : BaseWidget(view)

/**
 * @return [WebViewWidget] instance created from [parent].
 */
fun WebViewWidget(parent: ViewGroupWidget): WebViewWidget = WebViewWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))