package io.sketchware.models.sketchwarepro

import kotlinx.serialization.Serializable

@Serializable
data class ProguardConfig(
    /* boolean string. sw pro dev is idiot */
    val enabled: String,
    /* boolean string. sw pro dev is idiot */
    val debug: String
)