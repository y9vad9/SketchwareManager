package `fun`.kotlingang.sketchware.internal.exceptions

class LibraryNotFoundException internal constructor(libraryName: String) :
    Exception("Library with name $libraryName not found.")