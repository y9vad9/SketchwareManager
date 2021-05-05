package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.interfaces.objects.Library

data class AdMobLibrary(
    val units: MutableList<AdUnit>,
    val testDevices: MutableList<AdMobTestDevice>,
    val bannerAd: AdUnit,
    val interstitialAd: AdUnit,
    override var isEnabled: Boolean
) : Library {
    /**
     * Converts [AdMobLibrary] to [LibraryDataModel] which originally formatted for sketchware.
     * @return [LibraryDataModel] converted from current instance.
     */
    override fun toLibraryDataModel() = LibraryDataModel(
        units, "", LibraryType.ADMOB, "${bannerAd.name} : ${bannerAd.id}",
        "${interstitialAd.name} : ${interstitialAd.id}", "", testDevices, isEnabled
    )
}
