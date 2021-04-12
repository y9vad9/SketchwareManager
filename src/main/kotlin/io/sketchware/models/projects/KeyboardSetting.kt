package io.sketchware.models.projects

import io.sketchware.interfaces.IdInterface
import io.sketchware.utils.SWConst

enum class KeyboardSetting(override val id: Int) : IdInterface {
    Unspecified(SWConst.KEYBOARD_STATE_UNSPECIFIED),
    Visible(SWConst.KEYBOARD_STATE_VISIBLE),
    Hidden(SWConst.KEYBOARD_STATE_HIDDEN)
}