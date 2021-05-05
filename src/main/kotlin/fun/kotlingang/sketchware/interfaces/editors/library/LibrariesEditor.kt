package `fun`.kotlingang.sketchware.interfaces.editors.library

import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.objects.project.library.*

interface LibrariesEditor : Editor {

    /**
     * @return [AdMobLibrary] with data about admob library in project.
     */
    val admob: AdMobLibrary

    /**
     * @return [AppCompatLibrary] with data about appcompat library in project.
     */
    val appcompat: AppCompatLibrary

    /**
     * @return [GoogleMapLibrary] with data about google map library in project.
     */
    val googleMap: GoogleMapLibrary

    /**
     * @return [FirebaseLibrary] with data about firebase in project.
     */
    val firebase: FirebaseLibrary

    /**
     * Gets library by [name].
     * @param name - name of library in block bean.
     */
    fun getLibrary(name: String): LibraryDataModel

}