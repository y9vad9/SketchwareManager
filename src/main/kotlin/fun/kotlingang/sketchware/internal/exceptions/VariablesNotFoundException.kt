package `fun`.kotlingang.sketchware.internal.exceptions

class VariablesNotFoundException internal constructor(activity: String) :
    Exception("Variables not found for activity $activity. Does your activity exist?")