package io.sketchware.util.builders

import io.sketchware.model.project.view.LayoutOrientation
import io.sketchware.model.project.view.WidgetProperties
import io.sketchware.model.project.view.WidgetType

class WidgetBuilder(private val rootProperties: WidgetProperties) {
    fun linearLayout(id: String, orientation: LayoutOrientation, builder: WidgetBuilder.() -> Unit) {
        getBaseWidget(id, WidgetType.LINEAR_LAYOUT.id).also {
            it.layout.orientation = orientation
        }
    }

    private fun getBaseWidget(id: String, typeId: Int): WidgetProperties {
        return WidgetProperties(
            typeId = typeId,
            id = id,
            parent = rootProperties.id,
            parentTypeId = rootProperties.typeId,
            preParent = rootProperties.parent,
            preParentTypeId = rootProperties.parentTypeId
        )
    }
}