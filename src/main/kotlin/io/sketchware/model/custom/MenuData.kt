package io.sketchware.model.custom

import kotlinx.serialization.Serializable

@Serializable
data class MenuData(
    val title: String,
    val value: String
)
