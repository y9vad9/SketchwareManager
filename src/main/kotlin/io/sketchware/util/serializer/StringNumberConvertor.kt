package io.sketchware.util.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * Uses to fix numbers in string type.
 */
internal class StringNumberConvertor(
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("stringToInt")
) : KSerializer<Int> {
    override fun deserialize(decoder: Decoder): Int {
        return decoder.decodeString().toInt()
    }

    override fun serialize(encoder: Encoder, value: Int) {
        encoder.encodeString(value.toString())
    }
}