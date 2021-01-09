package io.sketchware.exceptions.projects

class SketchwareFileError(path: String) : Exception("File at path $path isn't exists or it isn't a file.")