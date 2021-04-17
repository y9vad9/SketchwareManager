package io.sketchware.util.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object YesNoSerializer : KSerializer<Boolean> {
    override fun deserialize(decoder: Decoder) = when (decoder.decodeString().toLowerCase()) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("YesNo property should consist only from [Y|N] values.")
    }

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("YesNoSerializer")

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.encodeString(
            if (value) "Y"
            else "N"
        )
    }
}