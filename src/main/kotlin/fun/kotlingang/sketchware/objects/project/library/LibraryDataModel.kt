package `fun`.kotlingang.sketchware.objects.project.library

import `fun`.kotlingang.sketchware.internal.json.serializers.YesNoSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Sketchware library data.
 */
@Serializable
data class SketchwareLibraryDataModel(
    /**
     * Ad units list (empty if it isn't admob component.
     */
    var adUnits: List<AdUnit>,
    var data: String,
    /**
     * Unique library type id.
     */
    var libType: Int,
    /**
     * Any data about a library (may be missing) is different everywhere.
     */
    var reserved1: String,
    /**
     * Any data about a library (may be missing) is different everywhere.
     */
    var reserved2: String,
    /**
     * Any data about a library (may be missing) is different everywhere.
     */
    var reserved3: String,
    /**
     * List of test devices (may be missing if it isn't admob library).
     */
    var testDevices: List<String>,
    /**
     * Is library enabled.
     */
    @SerialName("useYn")
    @Serializable(YesNoSerializer::class)
    var isEnabled: Boolean
)

@Serializable
data class AdUnit(
    val id: String,
    val name: String
)