package io.sketchware.project.models.sketchwarepro

import kotlinx.serialization.Serializable

@Serializable
data class ProguardData(
    /* boolean string. sw pro dev is idiot */
    val enabled: String,
    /* boolean string. sw pro dev is idiot */
    val debug: String
)