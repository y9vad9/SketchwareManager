package io.sketchware.util.serializer

import io.sketchware.model.project.BlockModel
import io.sketchware.util.internal.BeansParser
import io.sketchware.util.internal.BeansParser.toSaveableValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ListBlockModelSerializer : KSerializer<List<BlockModel>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("listBlockModelSerializer")

    override fun deserialize(decoder: Decoder): List<BlockModel> {
        return BeansParser.parseBeans(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: List<BlockModel>) {
        encoder.encodeString(value.toSaveableValue())
    }

}