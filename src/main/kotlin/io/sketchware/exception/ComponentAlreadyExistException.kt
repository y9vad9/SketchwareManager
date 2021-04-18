package io.sketchware.exception

class ComponentAlreadyExistException(activity: String, componentId: String)
    : Exception("Component with id $componentId already exist in $activity.")