package io.sketchware.exception

class EventAlreadyExistsException(activity: String, targetId: String, eventName: String) :
    Exception("Event for $targetId with name $eventName already exists in $activity.")