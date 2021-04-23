package `fun`.kotlingang.sketchware.internal.exceptions

class EventAlreadyExistsException internal constructor(activity: String, targetId: String, eventName: String) :
    Exception("Event for $targetId with name $eventName already exists in $activity.")