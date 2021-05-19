package `fun`.kotlingang.sketchware.interfaces.editors.logic

import `fun`.kotlingang.sketchware.interfaces.editors.LocalEditor
import `fun`.kotlingang.sketchware.internal.exceptions.EventAlreadyExistsException
import `fun`.kotlingang.sketchware.internal.exceptions.MoreblockAlreadyExistException
import `fun`.kotlingang.sketchware.objects.project.logic.*

/**
 * Works with the logic of a specific activity.
 */
interface ActivityLogicEditor : LocalEditor {

    /**
     * Gets list of moreBlocks in specific activity.
     * @return [List] with [MoreBlockModel] items.
     */
    val moreBlocks: List<MoreBlockEditor>

    /**
     * Gets list of events and returns it as editor.
     * @return [List] of [ActivityEventEditor].
     */
    val events: List<ActivityEventEditor>

    /**
     * Gets list of variables in current activity.
     * Gets only single variables such as String, Number, Boolean, Map.
     * @return [MutableList] of [VariableModel] which can be edited.
     */
    val variables: MutableList<VariableModel>

    /**
     * Gets list of list variables in current activity.
     * Gets only variables such as List String, List Number, List Map.
     * @return [MutableList] of [ListVariableModel] which can be edited.
     */
    val listVariables: MutableList<ListVariableModel>

    /**
     * Gets list of components is current activity.
     * @return [MutableList] of [ComponentModel] which can be edited.
     */
    val components: MutableList<ComponentModel>

    /**
     * Adds moreblock to current activity.
     * @param name - name of new moreblock (should have only english words, without space).
     * @param spec - Spec of moreblock.
     * @param blocks - list of blocks in event. If null or empty, it won't create block bean in logic.
     * @throws MoreblockAlreadyExistException if moreblock with such [name] already exist.
     */
    @Throws(MoreblockAlreadyExistException::class)
    fun addMoreblock(name: String, spec: List<SpecField>, blocks: List<BlockProperties>? = null): MoreBlockEditor


    /**
     * Adds moreblock to current activity.
     * @param event - event model with data about event.
     * @param blocks - list of blocks in event. If null or empty, it won't create block bean in logic.
     * @throws MoreblockAlreadyExistException if moreblock with such [EventModel.name] already exist.
     */
    @Throws(EventAlreadyExistsException::class)
    fun addEvent(event: EventModel, blocks: List<BlockProperties>? = null): MoreBlockEditor

}