package `fun`.kotlingang.sketchware.objects.project.logic.blocks

/**
 * The root of any block that returns primitive value such as string, number, boolean.
 */
sealed interface PrimitiveBlock : ReturnableBlock

sealed interface BooleanPrimitiveBlock : PrimitiveBlock

sealed interface NumberPrimitiveBlock : PrimitiveBlock

sealed interface StringPrimitiveBlock : PrimitiveBlock