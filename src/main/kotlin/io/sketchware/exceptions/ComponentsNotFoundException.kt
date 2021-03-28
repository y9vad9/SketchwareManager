package io.sketchware.exceptions

class ComponentsNotFoundException(activityName: String) :
    Exception("Components not found for activity $activityName.")