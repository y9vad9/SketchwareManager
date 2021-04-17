package io.sketchware.exception

class ComponentsNotFoundException(activityName: String) :
    Exception("Components not found for activity $activityName.")