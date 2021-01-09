package io.sketchware.exceptions.projects

import java.lang.Exception

class SketchwareFileError(path: String): Exception("File at path $path isn't exists or it isn't a file.")