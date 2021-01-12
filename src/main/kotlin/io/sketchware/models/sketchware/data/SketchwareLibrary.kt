package io.sketchware.models.sketchware.data

import kotlinx.serialization.Serializable

@Serializable
data class SketchwareLibrary(
    var name: String,
    var information: SketchwareLibraryData
)

@Serializable
data class SketchwareLibraryData(
    var adUnits: List<String>,
    var data: String,
    var libType: Int,
    var reserved1: String,
    var reserved2: String,
    var reserved3: String,
    var testDevices: List<String>,
    var useYn: String
)