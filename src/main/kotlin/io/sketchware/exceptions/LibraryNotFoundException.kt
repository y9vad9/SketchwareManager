package io.sketchware.exceptions

class LibraryNotFoundException(libraryName: String) :
    Exception("Library with name $libraryName not found.")