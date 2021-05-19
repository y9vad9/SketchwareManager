package `fun`.kotlingang.sketchware.interfaces.editors.logic

import `fun`.kotlingang.sketchware.interfaces.editors.LocalEditor
import `fun`.kotlingang.sketchware.objects.project.logic.BlockProperties
import `fun`.kotlingang.sketchware.objects.project.logic.EventType
import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetType

/**
 * Works with event of specific activity.
 */
interface ActivityEventEditor : LocalEditor {

    /**
     * @return [String] with name of current event.
     */
    var name: String

    /**
     * @return [EventType] with type of current event.
     */
    var eventType: EventType

    /**
     * Target widget id.
     * @return [String] with target id (if [EventType] is [EventType.WIDGET_EVENT] then it's widget id,
     * if it is [EventType.COMPONENT_EVENT] then it's component id, or
     * if it is [EventType.ACTIVITY_EVENT] then it's name of activity event).
     */
    var targetId: String

    /**
     * Target widget type.
     * @return event's target widget [WidgetType] of current event.
     */
    var targetType: WidgetType

    /**
     * @return [MutableList] of blocks which can be edited.
     */
    val blocks: MutableList<BlockProperties>

}