package io.sketchware.util.internal

import io.sketchware.model.project.content.ActivityTheme
import io.sketchware.model.project.content.FileType
import io.sketchware.model.project.content.KeyboardSetting
import io.sketchware.model.project.content.Orientation
import io.sketchware.model.project.logic.ListType
import io.sketchware.model.project.logic.VariableType
import io.sketchware.model.project.view.LayoutOrientation
import io.sketchware.model.project.view.SpinnerMode
import io.sketchware.model.project.view.TextStyle
import io.sketchware.model.project.view.WidgetType
import io.sketchware.util.internal.serializer.idSerializer
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
    contextual(idSerializer<VariableType>())
    contextual(idSerializer<ListType>())
    contextual(idSerializer(WidgetType.VIEW))
    contextual(idSerializer<TextStyle>())
    contextual(idSerializer<SpinnerMode>())
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