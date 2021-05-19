package `fun`.kotlingang.sketchware.interfaces.editors.logic

import `fun`.kotlingang.sketchware.interfaces.editors.LocalEditor
import `fun`.kotlingang.sketchware.objects.project.logic.BlockProperties
import `fun`.kotlingang.sketchware.objects.project.logic.SpecField

/**
 * Works with moreBlocks (it's logic, name, spec).
 */
interface MoreBlockEditor : LocalEditor {

    /**
     * Gets moreBlock name.
     * @return [String] with name of moreBlock.
     */
    var name: String

    /**
     * Gets moreblock spec.
     * @return [MutableList] of [SpecField] items.
     */
    val spec: MutableList<SpecField>


    /**
     * Gets list of blocks in moreBlock's logic.
     * @return [MutableList] with blocks which can be edited.
     */
    val blocks: MutableList<BlockProperties>

}