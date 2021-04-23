package `fun`.kotlingang.sketchware.internal.exceptions

class EventNotFoundException internal constructor(activity: String, targetId: String, eventName: String) :
    Exception("Event with name $eventName not found in $activity for $targetId.")