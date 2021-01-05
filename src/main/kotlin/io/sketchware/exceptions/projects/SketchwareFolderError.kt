package io.sketchware.exceptions.projects

import java.lang.Exception

class SketchwareFolderError(path: String): Exception("Sketchware folder is invalid. Check the folder along the path: $path")