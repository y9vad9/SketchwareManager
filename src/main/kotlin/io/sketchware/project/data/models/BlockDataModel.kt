package io.sketchware.project.data.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class BlockDataModel(
    val name: String,
    val values: List<JsonObject>
)