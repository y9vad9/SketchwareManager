package io.sketchware.models.sketchware.data

import kotlinx.serialization.Serializable

@Serializable
data class SketchwareLibrary(
    val name: String,
    val information: SketchwareLibraryData
)

@Serializable
data class SketchwareLibraryData(
    val adUnits: List<String>,
    val data: String,
    val libType: Int,
    val reserved1: String,
    val reserved2: String,
    val reserved3: String,
    val testDevices: List<String>,
    val useYn: String
)