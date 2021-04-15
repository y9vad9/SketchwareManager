package io.sketchware.utils.serializers

import io.sketchware.models.projects.BlockModel
import io.sketchware.utils.internal.TagFormatter
import io.sketchware.utils.internal.TagFormatter.toSaveableValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ListBlockModelSerializer : KSerializer<List<BlockModel>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("listBlockSerializer")

    override fun deserialize(decoder: Decoder): List<BlockModel> {
        return TagFormatter.parseAsArray(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: List<BlockModel>) {
        encoder.encodeString(value.toSaveableValue())
    }

}