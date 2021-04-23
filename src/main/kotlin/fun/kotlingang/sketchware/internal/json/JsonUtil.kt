package `fun`.kotlingang.sketchware.internal.json

import `fun`.kotlingang.sketchware.internal.json.serializers.idSerializer
import `fun`.kotlingang.sketchware.objects.project.content.ActivityTheme
import `fun`.kotlingang.sketchware.objects.project.content.FileType
import `fun`.kotlingang.sketchware.objects.project.content.Orientation
import `fun`.kotlingang.sketchware.objects.project.logic.ListType
import `fun`.kotlingang.sketchware.objects.project.logic.VariableType
import `fun`.kotlingang.sketchware.objects.project.view.properties.LayoutOrientation
import `fun`.kotlingang.sketchware.objects.project.view.properties.SpinnerMode
import `fun`.kotlingang.sketchware.objects.project.view.properties.TextStyle
import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetType
import io.sketchware.model.project.content.KeyboardSetting
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