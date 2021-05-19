package `fun`.kotlingang.sketchware.objects.project.logic.arguments

import `fun`.kotlingang.sketchware.interfaces.objects.MutableValueContainer
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.BooleanPrimitiveBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.NumberPrimitiveBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.StringPrimitiveBlock

/**
 * The root of any primitive argument
 */
sealed interface PrimitiveArgument : Argument

/**
 * Marks a primitive argument as a string. Parent of [StringNonExpressibleArgument] and [StringExpressibleArgument]ю
 */
sealed interface StringPrimitiveArgument : PrimitiveArgument

/**
 * Marks string as not expressible (which cannot have block arguments).
 */
sealed interface StringNonExpressibleArgument : StringPrimitiveArgument, NonExpressibleArgument

/**
 * Marks string as expressible (which can contain block).
 */
sealed interface StringExpressibleArgument : StringPrimitiveArgument, ExpressibleArgument

/**
 * Container for [String].
 */
class StringPrimitiveContainer(override var value: String?) : MutableValueContainer<String>,
    StringNonExpressibleArgument, StringExpressibleArgument

/**
 * Container for [StringPrimitiveBlock]
 */
class StringBlockContainer(override var value: StringPrimitiveBlock?) : MutableValueContainer<StringPrimitiveBlock>,
    StringExpressibleArgument


sealed interface NumberPrimitiveArgument : PrimitiveArgument

sealed interface NumberNonExpressibleArgument : NumberPrimitiveArgument, NonExpressibleArgument

sealed interface NumberExpressibleArgument : NumberPrimitiveArgument, NonExpressibleArgument

class NumberPrimitiveContainer(override var value: Double?) : MutableValueContainer<Double>,
    NumberNonExpressibleArgument, NumberExpressibleArgument

class NumberBlockContainer(override var value: NumberPrimitiveBlock?) : MutableValueContainer<NumberPrimitiveBlock>,
    NumberExpressibleArgument

sealed interface BooleanPrimitiveArgument : PrimitiveArgument

class BooleanBlockContainer(override var value: BooleanPrimitiveBlock?) : MutableValueContainer<BooleanPrimitiveBlock>,
    BooleanPrimitiveArgument
