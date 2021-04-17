package io.sketchware.util.serializer

import io.sketchware.model.project.content.ActivityOption
import io.sketchware.model.SWConst.ActivityOption.OPTION_ACTIVITY_DRAWER
import io.sketchware.model.SWConst.ActivityOption.OPTION_ACTIVITY_FAB
import io.sketchware.model.SWConst.ActivityOption.OPTION_ACTIVITY_FULL_SCREEN
import io.sketchware.model.SWConst.ActivityOption.OPTION_ACTIVITY_MASK
import io.sketchware.model.SWConst.ActivityOption.OPTION_ACTIVITY_TOOLBAR
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class ActivityOptionSerializer : KSerializer<List<ActivityOption>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ActivityOptionSerializer")

    override fun deserialize(decoder: Decoder) = mutableListOf<ActivityOption>().apply {
        val value = decoder.decodeInt()
        if ((value and OPTION_ACTIVITY_MASK) and OPTION_ACTIVITY_DRAWER == OPTION_ACTIVITY_DRAWER)
            add(ActivityOption.DRAWER)
        if ((value and OPTION_ACTIVITY_MASK) and OPTION_ACTIVITY_TOOLBAR == OPTION_ACTIVITY_TOOLBAR)
            add(ActivityOption.TOOLBAR)
        if ((value and OPTION_ACTIVITY_MASK) and OPTION_ACTIVITY_FAB == OPTION_ACTIVITY_FAB)
            add(ActivityOption.FAB)
        if ((value and OPTION_ACTIVITY_MASK) and OPTION_ACTIVITY_FULL_SCREEN == OPTION_ACTIVITY_FULL_SCREEN)
            add(ActivityOption.FULL_SCREEN)
    }.toList()

    override fun serialize(encoder: Encoder, value: List<ActivityOption>) {
        var output = 0
        value.forEach {
            output = output or it.id
        }
        encoder.encodeInt(output)
    }
}