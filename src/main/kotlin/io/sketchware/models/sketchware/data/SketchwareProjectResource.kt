package io.sketchware.models.sketchware.data

import kotlinx.serialization.SerialName

data class SketchwareProjectResource(
    /**
     * Full name of resource (example: logo.png)
     */
    @SerialName("resFullName")
    val fullName: String,
    /**
     * Resource name (example: logo)
     */
    @SerialName("resName")
    val name: String,
    @SerialName("resType")
    val type: Int
)