package io.sketchware.exception

class SpecFieldArgumentException(fieldText: String) :
    Exception("Spec field isn't an argument. Spec field value: $fieldText")