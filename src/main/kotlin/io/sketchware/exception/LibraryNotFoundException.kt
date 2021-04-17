package io.sketchware.exception

class LibraryNotFoundException(libraryName: String) :
    Exception("Library with name $libraryName not found.")