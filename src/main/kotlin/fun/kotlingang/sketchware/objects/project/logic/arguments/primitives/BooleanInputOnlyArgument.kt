package `fun`.kotlingang.sketchware.objects.project.logic.arguments.primitives

import `fun`.kotlingang.sketchware.interfaces.objects.InputOnly
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.PrimitiveArgument

sealed class BooleanInputOnlyArgument(override var input: Boolean?) : PrimitiveArgument(), InputOnly<Boolean>