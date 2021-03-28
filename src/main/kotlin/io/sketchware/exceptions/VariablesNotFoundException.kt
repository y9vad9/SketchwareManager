package io.sketchware.exceptions

class VariablesNotFoundException(activity: String) :
    Exception("Variables not found for activity $activity. Does your activity exist?")