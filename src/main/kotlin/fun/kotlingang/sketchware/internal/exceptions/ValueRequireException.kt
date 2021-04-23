package `fun`.kotlingang.sketchware.internal.exceptions

class ValueRequireException internal constructor(valueName: String, description: String = "") :
    Exception("Value for name [$valueName] should be specified. $description")