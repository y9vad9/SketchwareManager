package io.sketchware.model.custom

import kotlinx.serialization.Serializable

@Serializable
data class SWStudioMenu(
    val name: String,
    val title: String,
    val data: List<String>
)