package `fun`.kotlingang.sketchware.internal.json.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File

internal class PathToFileSerializer(
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Path2FileSerializer")
) : KSerializer<File> {
    override fun deserialize(decoder: Decoder) = File(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: File) = encoder.encodeString(value.path)
}