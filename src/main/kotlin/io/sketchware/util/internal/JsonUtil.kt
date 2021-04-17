package io.sketchware.util.internal

import io.sketchware.model.project.ActivityTheme
import io.sketchware.model.project.FileType
import io.sketchware.model.project.KeyboardSetting
import io.sketchware.model.project.Orientation
import io.sketchware.model.view.LayoutOrientation
import io.sketchware.util.serializer.idSerializer
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
    contextual(idSerializer<ActivityTheme>())
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