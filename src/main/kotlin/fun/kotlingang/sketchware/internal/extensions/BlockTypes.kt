package `fun`.kotlingang.sketchware.internal.extensions

import `fun`.kotlingang.sketchware.objects.project.logic.BlockType

internal val BlockType.isReturningVoid
    get() = this == BlockType.REGULAR || this == BlockType.IF || this == BlockType.IF_ELSE || this == BlockType.STOP