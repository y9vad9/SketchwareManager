package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.interfaces.objects.Library

data class FirebaseLibrary(
    var projectId: String,
    var appId: String,
    var apiKey: String,
    var storageBucketUrl: String,
    override var isEnabled: Boolean
): Library {
    /**
     * Converts [FirebaseLibrary] to [LibraryDataModel] which originally formatted for sketchware.
     * @return [LibraryDataModel] converted from current instance.
     */
    override fun toLibraryDataModel() = LibraryDataModel(
        listOf(), projectId, LibraryType.FIREBASE, appId, apiKey, storageBucketUrl, listOf(), isEnabled
    )
}
