package io.sketchware.model.project.content

import io.sketchware.`interface`.IdInterface
import io.sketchware.model.SWConst

enum class KeyboardSetting(override val id: Int) : IdInterface {
    UNSPECIFIED(SWConst.KeyboardOption.KEYBOARD_STATE_UNSPECIFIED),
    VISIBLE(SWConst.KeyboardOption.KEYBOARD_STATE_VISIBLE),
    HIDDEN(SWConst.KeyboardOption.KEYBOARD_STATE_HIDDEN)
}