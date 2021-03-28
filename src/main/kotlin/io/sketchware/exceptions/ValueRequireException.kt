package io.sketchware.exceptions

class ValueRequireException(valueName: String, description: String = "") :
    Exception("Value for name [$valueName] should be specified. $description")