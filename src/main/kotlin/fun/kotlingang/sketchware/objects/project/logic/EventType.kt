package `fun`.kotlingang.sketchware.objects.project.logic

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable

/**
 * Says event type (for example: is it WIDGET event or not.).
 */
enum class EventType(override val id: Int) : Identifiable {
    /**
     * Says that the event is intended for a widget.
     */
    WIDGET_EVENT(1),

    /**
     * Says that the event is intended for a component (for example: onFilesPicked event of FilePicker component).
     */
    COMPONENT_EVENT(2),

    /**
     * Says that the event is intended for a activity.
     */
    ACTIVITY_EVENT(3),

    /**
     * Says that the event is intended for a drawer's widgets.
     */
    DRAWER_EVENT(4)
}