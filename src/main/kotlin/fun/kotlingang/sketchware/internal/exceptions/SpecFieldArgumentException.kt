package `fun`.kotlingang.sketchware.internal.exceptions

class SpecFieldArgumentException internal constructor(fieldText: String) :
    Exception("Spec field isn't an argument. Spec field value: $fieldText")