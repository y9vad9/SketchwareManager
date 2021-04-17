package io.sketchware.model.project

import io.sketchware.`interface`.IdInterface
import io.sketchware.util.SWConst

enum class KeyboardSetting(override val id: Int) : IdInterface {
    Unspecified(SWConst.KEYBOARD_STATE_UNSPECIFIED),
    Visible(SWConst.KEYBOARD_STATE_VISIBLE),
    Hidden(SWConst.KEYBOARD_STATE_HIDDEN)
}