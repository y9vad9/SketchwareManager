package `fun`.kotlingang.sketchware.internal.json.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object IntToBooleanSerializer : KSerializer<Boolean> {
    override fun deserialize(decoder: Decoder) = decoder.decodeInt() != 0

    override val descriptor = buildClassSerialDescriptor("IntToBoolean")

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeInt(if (value) 1 else 0)
    }
}