package io.sketchware.exceptions.projects

class SketchwareFolderError(path: String) :
    Exception("Sketchware folder is invalid. Check the folder along the path: $path")