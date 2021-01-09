package io.sketchware.project.models

import kotlinx.serialization.Serializable

@Serializable
data class SketchwareProjectBlock(
    val color: Int,
    val id: String,
    val nextBlock: Int,
    val opCode: String,
    val parameters: List<String>,
    val spec: String,
    val subStack1: Int,
    val subStack2: Int,
    val type: String,
    val typeName: String
)