package io.sketchware.utils.serializers

import io.sketchware.models.*
import io.sketchware.models.projects.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SpecSerializer(
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SpecSerializer")
) : KSerializer<List<SpecField>> {
    override fun deserialize(decoder: Decoder): List<SpecField> {
        return decoder.decodeString().toSpecFields()
    }

    override fun serialize(encoder: Encoder, value: List<SpecField>) {
        encoder.encodeString(value.joinToString(" "))
    }
}

internal fun String.toSpecFields(): List<SpecField> {
    return split(" ").map {
        return@map when {
            "%d" in it -> NumberSpecArgument(it)
            "%s" in it -> StringSpecArgument(it)
            "%b" in it -> BooleanSpecArgument(it)
            "%m" in it -> MenuSpecArgument(it)
            else -> SpecField(it)
        }
    }
}