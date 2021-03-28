package io.sketchware.utils.internal

import io.sketchware.models.projects.FileType
import io.sketchware.models.projects.KeyboardSetting
import io.sketchware.models.projects.Orientation
import io.sketchware.models.view.LayoutOrientation
import io.sketchware.utils.serializers.SpecSerializer
import io.sketchware.utils.serializers.idSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

private val serializationModule = SerializersModule {
    contextual(idSerializer<FileType>())
    contextual(idSerializer<KeyboardSetting>())
    contextual(idSerializer<Orientation>())
    contextual(idSerializer(LayoutOrientation.HORIZONTAL))
    contextual(SpecSerializer())
}

internal val json = Json {
    serializersModule = serializationModule
}

internal inline fun <reified T> String.serialize() = json.decodeFromString<T>(this)
internal fun <T> String.serialize(kSerializer: KSerializer<T>) =
    json.decodeFromString(kSerializer, this)

internal inline fun <reified T> T.deserialize() = json.encodeToString(this)
internal fun <T> T.deserialize(kSerializer: KSerializer<T>) =
    json.encodeToString(kSerializer, this)