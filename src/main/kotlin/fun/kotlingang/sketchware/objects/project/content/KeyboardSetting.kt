package io.sketchware.model.project.content

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable
import `fun`.kotlingang.sketchware.objects.SWConst

enum class KeyboardSetting(override val id: Int) : Identifiable {
    UNSPECIFIED(SWConst.KeyboardOption.KEYBOARD_STATE_UNSPECIFIED),
    VISIBLE(SWConst.KeyboardOption.KEYBOARD_STATE_VISIBLE),
    HIDDEN(SWConst.KeyboardOption.KEYBOARD_STATE_HIDDEN)
}