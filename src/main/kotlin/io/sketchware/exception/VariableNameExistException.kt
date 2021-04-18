package io.sketchware.exception

class VariableNameExistException(activityName: String, variableName: String) :
    Exception(
        "Variable in $activityName with name $variableName already exist." +
                " Add variable with unique name or set [force] argument to true."
    )