package `fun`.kotlingang.sketchware.internal.json.serializers

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class IdEnumSerializer<T : Enum<T>>(
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("IdEnumSerializer"),
    private val map: Map<Int, T>, private val defaultValue: T? = null
) : KSerializer<T> {
    override fun deserialize(decoder: Decoder) =
        map[decoder.decodeInt()]
            ?: defaultValue
            ?: error("Unexpected id ${decoder.decodeInt()}, defaultValue isn't specified.")

    override fun serialize(encoder: Encoder, value: T) =
        encoder.encodeInt((map.values.find { it == value } as Identifiable).id)
}

internal inline fun <reified T : Enum<T>> idSerializer(defaultValue: T? = null) =
    IdEnumSerializer(map = enumValues<T>().associateBy {
        (it as Identifiable).id
    }, defaultValue = defaultValue)