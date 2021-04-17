package io.sketchware.exception

class ValueRequireException(valueName: String, description: String = "") :
    Exception("Value for name [$valueName] should be specified. $description")