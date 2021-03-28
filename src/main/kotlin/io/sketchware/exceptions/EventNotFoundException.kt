package io.sketchware.exceptions

class EventNotFoundException(activity: String, targetId: String, eventName: String) :
    Exception("Event with name $eventName not found in $activity for $targetId.")