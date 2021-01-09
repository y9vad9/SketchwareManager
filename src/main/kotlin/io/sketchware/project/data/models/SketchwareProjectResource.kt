package io.sketchware.project.data.models

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