package io.sketchware.utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

inline fun <reified T> String.serialize(): T {
    return Json.decodeFromString(this)
}


internal inline fun <reified T> ByteArray.serialize() = String(this).serialize<T>()

inline fun <reified T> JsonObject.toModel() = Json.decodeFromJsonElement<T>(this)

inline fun <reified T> T.toJson() = Json.encodeToString(this)