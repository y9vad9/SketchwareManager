package `fun`.kotlingang.sketchware.internal.exceptions

class ComponentsNotFoundException internal constructor(activityName: String) :
    Exception("Components not found for activity $activityName.")