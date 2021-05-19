package `fun`.kotlingang.sketchware.objects.project.logic.blocks

/**
 * The root of any block that returns structure such as map, list.
 */
sealed interface StructuredBlock : ReturnableBlock

sealed interface MapBlock : StructuredBlock

sealed interface ListBlock : StructuredBlock

sealed interface ListStringBlock : ListBlock

sealed interface ListNumberBlock : ListBlock

sealed interface ListMapBlock : ListBlock