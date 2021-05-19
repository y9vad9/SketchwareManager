package `fun`.kotlingang.sketchware.internal.json.serializers

import `fun`.kotlingang.sketchware.internal.parsers.BeansParser
import `fun`.kotlingang.sketchware.internal.parsers.BeansParser.toSaveableValue
import `fun`.kotlingang.sketchware.objects.project.logic.BlockProperties
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object ListBlockModelSerializer : KSerializer<List<BlockProperties>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("listBlockModelSerializer")

    override fun deserialize(decoder: Decoder): List<BlockProperties> {
        return BeansParser.parseBeans(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: List<BlockProperties>) {
        encoder.encodeString(value.toSaveableValue())
    }

}