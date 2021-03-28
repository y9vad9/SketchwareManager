package io.sketchware.exceptions

class VariableNameExistException(activityName: String, variableName: String) :
    Exception("Variable with name $variableName already exist in $activityName.")