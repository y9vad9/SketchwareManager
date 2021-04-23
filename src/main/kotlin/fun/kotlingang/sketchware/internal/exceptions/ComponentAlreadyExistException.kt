package `fun`.kotlingang.sketchware.internal.exceptions

class ComponentAlreadyExistException internal constructor(activity: String, componentId: String) :
    Exception("Component with id $componentId already exist in $activity.")