package `fun`.kotlingang.sketchware.internal.json.serializers

import `fun`.kotlingang.sketchware.objects.project.logic.BlockType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class BlockTypeSerializer : KSerializer<BlockType> {
    override fun deserialize(decoder: Decoder): BlockType {
        return BlockType.values().find { it.typeName == decoder.decodeString() }
            ?: error("Invalid input ${decoder.decodeString()}")
    }

    override val descriptor = buildClassSerialDescriptor("blockTypeSerializer")

    override fun serialize(encoder: Encoder, value: BlockType) {
        encoder.encodeString(value.typeName)
    }
}