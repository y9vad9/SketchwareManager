package `fun`.kotlingang.sketchware.internal.exceptions

class VariableNameExistException internal constructor(activityName: String, variableName: String) :
    Exception(
        "Variable in $activityName with name $variableName already exist." +
            " Add variable with unique name or set [force] argument to true."
    )