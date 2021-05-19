package `fun`.kotlingang.sketchware.objects.project.logic.arguments

import `fun`.kotlingang.sketchware.interfaces.objects.MutableValueContainer
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.ListMapBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.ListNumberBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.ListStringBlock
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.MapBlock

sealed interface StructuredArgument : Argument

sealed interface MapArgument : StructuredArgument

class MapVariableNameContainer(override var value: String?) : MapArgument, ExpressibleArgument,
    MutableValueContainer<String>

class MapBlockContainer(override var value: MapBlock?) : MapArgument, ExpressibleArgument,
    MutableValueContainer<MapBlock>

sealed interface ListArgument : StructuredArgument, ReferenceArgument

sealed interface ListVariableNameArgument : ListArgument, NonExpressibleArgument, ExpressibleArgument

sealed interface ListBlockArgument : ListArgument, ExpressibleArgument

sealed interface ListMapArgument : ListArgument

class ListMapVariableNameContainer(override var value: String?) : ListVariableNameArgument,
    MutableValueContainer<String>, ListMapArgument

class ListMapBlockContainer(override var value: ListMapBlock?) : ListBlockArgument, MutableValueContainer<ListMapBlock>,
    ListMapArgument

sealed interface ListStringArgument : ListArgument

class ListStringVariableNameContainer(override var value: String?) : ListVariableNameArgument,
    MutableValueContainer<String>, ListStringArgument

class ListStringBlockContainer(override var value: ListStringBlock?) : ListBlockArgument,
    MutableValueContainer<ListStringBlock>, ListStringArgument

sealed interface ListNumberArgument : ListArgument

class ListNumberVariableNameContainer(override var value: String?) : ListVariableNameArgument,
    MutableValueContainer<String>, ListStringArgument

class ListNumberBlockContainer(override var value: ListNumberBlock?) : ListBlockArgument,
    MutableValueContainer<ListNumberBlock>, ListStringArgument
