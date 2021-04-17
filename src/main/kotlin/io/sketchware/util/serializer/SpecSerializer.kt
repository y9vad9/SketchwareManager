package io.sketchware.util.serializer

import io.sketchware.model.project.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object SpecSerializer : KSerializer<List<SpecField>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("SpecSerializer")

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