package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class SwitchWidget(view: WidgetProperties) : CheckboxWidget(view)

/**
 * @return [SwitchWidget] instance created from [parent].
 */
fun SwitchWidget(parent: ViewGroupWidget): SwitchWidget = SwitchWidget(WidgetProperties(
    parent = parent.id, parentTypeId = parent.view.typeId,
    preParent = parent.view.parent, preParentTypeId = parent.view.parentTypeId
))

