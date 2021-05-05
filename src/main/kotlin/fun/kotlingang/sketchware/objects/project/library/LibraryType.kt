package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable

enum class LibraryType(override val id: Int) : Identifiable {
    APPCOMPAT(1),
    FIREBASE(0),
    ADMOB(2),
    GOOGLE_MAP(3)
}