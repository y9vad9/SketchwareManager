package io.sketchware.exception

class InvalidMenuArgumentTypeException(argumentName: String) :
    Exception(
        "The unexpected type menu's argument under the name: $argumentName." +
                " Are you sure this is not a custom argument?"
    )