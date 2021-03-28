package io.sketchware.models.customs

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    val title: String,
    val value: String
)
