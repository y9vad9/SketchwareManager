package io.sketchware.manager.projects.util

import io.sketchware.`interface`.Editor
import io.sketchware.`interface`.listener.ActionFinishListener
import io.sketchware.annotation.ExperimentalSWManagerAPI
import io.sketchware.exception.*
import io.sketchware.model.project.*
import io.sketchware.model.project.logic.*
import io.sketchware.util.SketchwareEncryptor
import io.sketchware.util.SketchwareEncryptor.decrypt
import io.sketchware.util.internal.*
import io.sketchware.util.serializer.toSpecFields
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import kotlin.coroutines.CoroutineContext

class LogicManager(
    private var value: String,
    private val file: File,
    override val coroutineContext: CoroutineContext = Dispatchers.Default
) : CoroutineScope, Editor {

    companion object {
        suspend operator fun invoke(file: File) =
            LogicManager(file.readOrNull()?.decrypt()?.byteArrayToString() ?: "", file)
    }

    /**
     * Gets list of [Model] from tag with name [forName].
     * @param forName - Name of block.
     */
    private inline fun <reified Model> getBlock(forName: String) =
        BeansParser.getListByTag<Model>(forName, value)

    /**
     * Gets block with pairs (splitted by :) for name of tag [forName].
     */
    private fun getPairBlock(forName: String) =
        value.getByTag(forName.normalizeTag())?.let { BeansParser.parseTextBlocks(it) }

    private fun saveTag(name: String, stringToSave: String) {
        println("$name:\n$stringToSave")
        value = BeansParser.addTag(name, stringToSave, value)
        println(value)
    }

    /**
     * Saves all data which was edited / removed / modified async.
     * @param callback - function call when it will be finished.
     */
    override fun save(callback: ActionFinishListener?) = launch {
        save()
        callback?.onFinish()
    }

    /**
     * Updates data for [value] from [file].
     * Warning! It will erase all data what you have changed.
     */
    override suspend fun fetch() {
        value = file.read().decrypt().byteArrayToString()
    }

    /**
     * Updates data for [value] from [file].
     * Warning! It will erase all data what you have changed.
     * @param callback - will be called when it will be finished.
     */
    override fun fetch(callback: ActionFinishListener?) = launch {
        fetch()
        callback?.onFinish()
    }

    /**
     * Gets list of events from specific activity.
     * @param activity - activity name (example: MainActivity).
     */
    fun getEvents(activity: String) =
        getBlock<EventModel>("$activity.java_events")

    /**
     * Gets specific event.
     * @param activity - activity name (example: MainActivity).
     * @param targetId - target string id (example: button1).
     * @param eventName - event name (example: onClick).
     * @see getEvents
     * @return [EventModel] or null if event with
     * specific [targetId] and [eventName] in [activity] not found.
     */
    fun getEvent(activity: String, targetId: String, eventName: String) =
        getEvents(activity)?.firstOrNull { it.targetId == targetId && it.name == eventName }

    /**
     * Edits info about specific event.
     * Be careful! This API is experimental and can be removed.
     * @param activity - activity name (example: MainActivity).
     * @param targetId - target string id (example: button1).
     * @param eventName - event name (example: onClick).
     * @param newEventModel - new model to replace with exist one.
     * @throws EventsNotFoundException - if in specific [activity] does not exist any event.
     * @throws EventNotFoundException - if event with name [eventName] for [targetId] in [activity] not found.
     */
    @Throws(EventsNotFoundException::class, EventNotFoundException::class)
    @ExperimentalSWManagerAPI
    fun editEventInfo(
        activity: String,
        targetId: String,
        eventName: String,
        newEventModel: EventModel
    ) {
        val events = getEvents(activity)?.toMutableList() ?: throw EventsNotFoundException(activity)
        val index = events.indexOfFirst { it.targetId == targetId && it.name == eventName }
            .takeIf { it != -1 } ?: throw EventNotFoundException(activity, targetId, eventName)
        events[index] = newEventModel
        saveEvents(activity, events)
        if(targetId != newEventModel.targetId || eventName != newEventModel.name) {
            val eventLogic = getEventLogic(activity, targetId, eventName) ?: return
            removeEventLogic(activity, targetId, eventName)
            saveEventLogic(activity, newEventModel.targetId, newEventModel.name, eventLogic)
         }
    }

    /**
     * Edits event info.
     * Be careful! This API is experimental and can be removed.
     * @param activity - activity name (example: MainActivity).
     * @param targetId - target string id (example: button1).
     * @param eventName - event name (example: onClick).
     * @param editor - lambda with [EventModel] in context to edit.
     * @throws EventsNotFoundException - if in specific [activity] does not exist any event.
     * @throws EventNotFoundException - if event with name [eventName]
     * for [targetId] in activity [activity] not found
     */
    @Throws(EventNotFoundException::class, EventsNotFoundException::class)
    @ExperimentalSWManagerAPI
    fun editEventInfo(
        activity: String,
        targetId: String,
        eventName: String,
        editor: EventModel.() -> Unit
    ) = with(
        getEvent(activity, targetId, eventName)
            ?: throw EventNotFoundException(activity, targetId, eventName)
    ) {
        val list = getEvents(activity)?.toMutableList()
            ?: throw EventsNotFoundException(activity)
        list[list.indexOf(this)] = this.apply(editor)
        saveEvents(activity, list)
    }

    /**
     * Removes event in specific [activity].
     * @param activity - activity name (example: MainActivity)
     * @param targetId - target id (example: button1)
     * @param eventName - event name (example: onClick)
     * @return [Boolean] - true if successfully removed or
     * false if activity does not exist or no one event was created yet.
     */
    fun removeEvent(activity: String, targetId: String, eventName: String): Boolean {
        val events = getEvents(activity)?.toMutableList() ?: return false
        val isRemoved = events.removeIf { it.targetId == targetId && it.name == eventName }
        if(isRemoved)
            saveEvents(activity, events)
        return isRemoved
    }

    /**
     * Adds new event to specific [activity].
     * @param activity - activity name (example: MainActivity)
     * @param eventModel - event info.
     * @param force - indicates whether to force the addition of event even if such an event already exists.
     * @param logic - logic to save in event.
     * @throws EventAlreadyExistsException - if event already exist in [activity].
     */
    @Throws(EventAlreadyExistsException::class)
    fun addEvent(activity: String, eventModel: EventModel, logic: List<BlockModel>, force: Boolean = false) =
        saveEvents(activity, (getEvents(activity) ?: emptyList()).toMutableList().apply {
            if (!force && any { it.targetId == eventModel.targetId && it.name == eventModel.name })
                throw EventAlreadyExistsException(activity, eventModel.targetId, eventModel.name)
            add(eventModel)
        }).also {
            saveEventLogic(activity, eventModel.targetId, eventModel.name, logic)
        }

    /**
     * Gets list of list variables.
     * @param activity - activity name (for example: MainActivity, see
     * [io.sketchware.model.project.content.FileDataModel.javaName]).
     * @return [List] of list variables in [activity] or null if activity or list variables not exist.
     */
    fun getListVariables(activity: String) =
        getPairBlock("$activity.java_list")?.map {
            ListVariableModel(it.second, it.first.serialize())
        }

    /**
     * Gets variable in [activity] by [variableName].
     * @param activity - activity name (for example: MainActivity, see
     * [io.sketchware.model.project.content.FileDataModel.javaName]).
     * @param variableName - unique variable name.
     * @return [ListVariableModel] or null if activity, list variables
     * or variable for name [variableName] does not not exist.
     */
    fun getListVariable(activity: String, variableName: String) =
        getListVariables(activity)?.find { it.name == variableName }

    /**
     * Adds list variable to [activity].
     * @param activity - activity name (for example: MainActivity,
     * see [io.sketchware.model.project.content.FileDataModel.javaName]).
     * @param variableName - unique variable name (Similar names should not be in [activity]).
     * @param listType - list variable type (for example: [ListType.LIST_STRING]).
     * @param force - indicates whether to force the addition of
     * list variable even if such an list variable already exists.
     * @throws VariableNameExistException if variable with [variableName] already exist in [activity].
     */
    @Throws(VariableNameExistException::class)
    fun addListVariable(activity: String, variableName: String, listType: ListType, force: Boolean = false) {
        if(getListVariable(activity, variableName) != null && !force)
            throw VariableNameExistException(activity, variableName)
        saveListVariables(activity, getListVariables(activity).orEmpty().toMutableList().apply {
            add(ListVariableModel(variableName, listType))
        })
    }

    /**
     * Removes variable with name [variableName] in [activity].
     * @param activity - activity name (for example: MainActivity,
     * see [io.sketchware.model.project.content.FileDataModel.javaName]).
     * @param variableName - variable name to delete.
     * @return [Boolean] is variable removed or not (returns false
     * if there no variable with name [variableName] or no list variables in [activity]).
     */
    fun removeListVariable(activity: String, variableName: String): Boolean {
        val listVariables = getListVariables(activity)?.toMutableList() ?: return false
        val isRemoved = listVariables.removeIf { it.name == variableName }
        if(isRemoved)
            saveListVariables(activity, listVariables)
        return isRemoved
    }

    /**
     * Gets list of variables from specific activity.
     * @param activity - activity name (example: MainActivity).
     */
    fun getVariables(activity: String) = getPairBlock("$activity.java_var")?.map {
        VariableModel(it.first.serialize(), it.second)
    }

    /**
     * Gets variable in specific activity.
     * @param activity - activity name (example: MainActivity).
     * @param variableName - variable name.
     * @return [VariableModel] or null if it does not exist.
     */
    fun getVariable(activity: String, variableName: String) =
        getVariables(activity)?.firstOrNull { it.name == variableName }

    /**
     * Removes variable by [variableName].
     * @param activity - Activity name.
     * @param variableName - variable name.
     * @return [Boolean] - true if successfully removed or false if activity does not exist or
     * no one variable in activity was created.
     */
    fun removeVariable(activity: String, variableName: String): Boolean {
        val variables = getVariables(activity)?.toMutableList() ?: return false
        val isRemoved = variables.removeIf { it.name == variableName }
        if(isRemoved)
            saveVariables(activity, variables)
        return isRemoved
    }


    /**
     * Adds variable to [activity].
     * @param activity - activity name (example: MainActivity).
     * @param variableName - variable name (should be unique).
     * @param variableType - variable type.
     * @throws VariableNameExistException if variable with same [variableName] already exist.
     */
    @Throws(VariableNameExistException::class)
    fun addVariable(
        activity: String,
        variableName: String,
        variableType: VariableType,
        force: Boolean = false
    ) = saveVariables(
        activity, (getVariables(activity) ?: emptyList()).toMutableList().apply {
            if (any { it.name == variableName } && !force)
                throw VariableNameExistException(activity, variableName)
            add(VariableModel(variableType, variableName))
        }
    )

    /**
     * Gets components in specific activity.
     * @param activity - activity name (example: MainActivity)
     */
    fun getComponents(activity: String) =
        getBlock<ComponentModel>("$activity.java_components")

    /**
     * Gets component by [componentId] in specific [activity].
     * @param activity - activity name (example: MainActivity)
     * @param componentId - component id (name which user set).
     * @return [ComponentModel] or null if component does not exist.
     */
    fun getComponent(activity: String, componentId: String) =
        getComponents(activity)?.firstOrNull { it.id == componentId }

    /**
     * Removes component by [componentId] in specific [activity].
     * @param activity - activity name (example: MainActivity).
     * @param componentId - component id (name which user set).
     */
    fun removeComponent(activity: String, componentId: String): Boolean {
        val components = getComponents(activity)?.toMutableList() ?: return false
        val isRemoved = components.removeIf { it.id == componentId }
        if(isRemoved)
            saveComponents(activity, components)
        return isRemoved
    }

    /**
     * Adds component to specific [activity].
     * @param activity - activity name (example: MainActivity).
     * @param componentModel - info about component.
     */
    fun addComponent(activity: String, componentModel: ComponentModel, force: Boolean = false) =
        saveComponents(activity, (getComponents(activity) ?: emptyList()).toMutableList().apply {
            if(any { it.id == componentModel.id } && !force)
                throw ComponentAlreadyExistException(activity, componentModel.id)
            add(componentModel)
        })

    /**
     * Get activity moreblocks
     * @param activity activity name (Example: MainActivity)
     * @return List of [MoreblockModel] in specific activity or null
     * if activity / moreblocks doesn't exist.
     */
    fun getMoreblocks(activity: String) =
        getPairBlock("$activity.java_func")?.map { (name, data) ->
            MoreblockModel(name, data.toSpecFields())
        }

    /**
     * Gets moreblock by name.
     * @param activity - activity name (example: activity).
     * @param name - moreblock name.
     * @return [MoreblockModel] or null if moreblock does not exist.
     */
    fun getMoreblock(activity: String, name: String) =
        getMoreblocks(activity)?.firstOrNull { it.name == name }

    /**
     * Gets moreblock's logic.
     * @param activity - activity name (example: activity).
     * @param name - moreblock name.
     */
    fun getMoreblockLogic(activity: String, name: String) =
        getBlock<BlockModel>("$activity.java_${name}_moreBlock")

    /**
     * Edits moreblock's logic.
     * @param activity - activity name in which moreblock consist (example: MainActivity).
     * @param name - moreblock name.
     * @param newBlocks - blocks to save (old will be replaced).
     */
    fun editMoreblockLogic(
        activity: String, name: String, newBlocks: List<BlockModel>
    ) = saveTag("$activity.java_${name}_moreBlock", newBlocks.joinToString("\n") {
        it.deserialize()
    })

    /**
     * Edits moreblock's logic.
     * @param activity - activity name in which moreblock consist (example: MainActivity).
     * @param name - moreblock name.
     * @param editor - lambda with list of blocks in context to edit.
     * @throws MoreblockNotFoundException if moreblock not found in [activity].
     */
    @Throws(MoreblockNotFoundException::class)
    fun editMoreblockLogic(
        activity: String,
        name: String,
        editor: MutableList<BlockModel>.() -> Unit
    ) = editMoreblockLogic(
        activity, name, getMoreblockLogic(activity, name)?.toMutableList()
            ?.apply(editor) ?: throw MoreblockNotFoundException(activity, name)
    )

    /**
     * Edits moreblock info.
     * Note: it is experimental API and can be removed at any time.
     * @param activity - Activity name (example: MainActivity).
     * @param name - moreblock name.
     * @param editor - Moreblock info editor lambda.
     */
    @ExperimentalSWManagerAPI
    fun editMoreblockInfo(
        activity: String,
        name: String,
        editor: (MoreblockModel) -> Unit
    ) {
        val moreblocks = getMoreblocks(activity)?.toMutableList()
            ?: throw MoreblocksNotFoundException(activity)
        val moreblock = getMoreblock(activity, name)
            ?: throw MoreblockNotFoundException(activity, name)
        val index = moreblocks.indexOfFirst { it.name == moreblock.name }
            .takeUnless { it == -1 } ?: throw MoreblockNotFoundException(activity, moreblock.name)
        val newMoreblock = moreblock.apply(editor)
        moreblocks[index] = newMoreblock
        saveMoreblocks(activity, moreblocks)
        if(newMoreblock.name != name) {
            val logic = getMoreblockLogic(activity, name) ?: return
            removeEventLogic(activity, name, "moreBlock")
            saveEventLogic(activity, name, "moreBlock", logic)
        }
    }

    /**
     * Removes moreblock from specific [activity] by [name].
     * @param activity - activity name (example: MainActivity).
     * @param name - moreblock name.
     * @return [Boolean] - true if successfully removed or false if activity does not exist or
     * no one moreblock has been created yet.
     */
    fun removeMoreblock(activity: String, name: String): Boolean {
        val moreblocks = getMoreblocks(activity)?.toMutableList() ?: return false
        val isRemoved = moreblocks.removeIf { it.name == name }
        if(isRemoved) {
            removeBlock("$activity.java_${name}_moreBlock")
            saveMoreblocks(activity, moreblocks)
        }
        return isRemoved
    }

    /**
     * Adds moreblock to specific [activity].
     * @param activity - activity name (example: MainActivity, see
     * [io.sketchware.model.project.content.FileDataModel.javaName]).
     * @param name - moreblock name.
     * @param spec - moreblock spec (arguments, fields. Example: toast %s.message).
     * @param force - indicates whether to force the addition of an event, even if such an event already exists.
     */
    fun addMoreblock(activity: String, name: String, spec: List<SpecField>, force: Boolean = false) = saveMoreblocks(
        "$activity.java_func", getMoreblocks(activity).orEmpty().toMutableList().apply {
            if(any { it.name == name } && !force)
                throw MoreblockAlreadyExistException(activity, name)
            add(MoreblockModel(name, spec))
        }
    )

    /**
     * Gets logic of event.
     * @return blocks in event.
     */
    fun getEventLogic(activity: String, targetId: String, eventName: String) =
        getBlock<BlockModel>("$activity.java_${targetId}_$eventName")

    /**
     * Removes event logic in [activity] for [targetId] with name [eventName].
     * Note: it is experimental API only for testing. Can be removed in any time.
     * @param activity - activity name (for example: MainActivity).
     * @param targetId - target widget / component id (example: button1).
     * @param eventName - event name (example: onClick).
     */
    @ExperimentalSWManagerAPI
    fun removeEventLogic(activity: String, targetId: String, eventName: String) =
        removeBlock("$activity.${targetId}_$eventName")

    /**
     * Edits event [eventName] logic for specific [targetId].
     * @param activity - activity name (example: MainActivity).
     * @param targetId - target string id (example: button1).
     * @param eventName - event name (example: onClick).
     * @param list - list of new blocks to save.
     */
    fun editEventLogic(
        activity: String, targetId: String, eventName: String, list: List<BlockModel>
    ) = saveEventLogic(activity, targetId, eventName, list)

    /**
     * Edits event [eventName] logic for specific [targetId].
     * @param activity - activity name (example: MainActivity).
     * @param targetId - target string id (example: button1).
     * @param eventName - event name (example: onClick).
     * @param editor - lambda with mutable list to edit in.
     * @throws EventNotFoundException - if event not found.
     */
    @Throws(EventNotFoundException::class)
    fun editEventLogic(
        activity: String,
        targetId: String,
        eventName: String,
        editor: (blocks: MutableList<BlockModel>) -> Unit
    ) = editEventLogic(
        activity,
        targetId,
        eventName,
        getEventLogic(activity, targetId, eventName)
            ?.toMutableList()?.apply(editor)
            ?: throw EventNotFoundException(activity, targetId, eventName)
    )

    /**
     * Gets logic of onCreate in specific activity.
     * @param activity - activity name (example: MainActivity)
     */
    fun getOnCreateLogic(activity: String) =
        getEventLogic(activity, "onCreate", "initializeLogic")

    /**
     * Edits logic for onCreate in specific activity.
     * @param activity - activity name (example: MainActivity).
     * @param list - list of blocks to save.
     */
    fun editOnCreateLogic(activity: String, list: List<BlockModel>) =
        editEventLogic(activity, "onCreate", "initializeLogic", list)

    /**
     * Edits logic for onCreate in specific activity.
     * @param activity - activity name (example: MainActivity).
     * @param editor - lambda with mutable list to edit in.
     * @throws EventNotFoundException - if event doesn't exist.
     */
    @Throws(EventNotFoundException::class)
    fun editOnCreateLogic(activity: String, editor: (MutableList<BlockModel>) -> Unit) =
        editOnCreateLogic(
            activity, getOnCreateLogic(activity)?.toMutableList()?.apply(editor)
                ?: throw EventNotFoundException(activity, "onCreate", "initializeLogic")
        )

    private fun saveEvents(activity: String, list: List<EventModel>) =
        saveTag("$activity.java_events", list.joinToString("\n") {
            it.deserialize()
        })

    /**
     * Saved all data which was edited / removed / modified to [file].
     */
    override suspend fun save() = file.write(SketchwareEncryptor.encrypt(value.toByteArray()))

    private fun saveComponents(activity: String, list: List<ComponentModel>) =
        saveTag("$activity.java_components", list.joinToString("\n") {
            it.deserialize()
        })

    private fun saveMoreblocks(activity: String, list: List<MoreblockModel>) =
        saveTag("$activity.java_func", list.joinToString("\n"))

    private fun saveEventLogic(
        activity: String, targetId: String, eventName: String, list: List<BlockModel>
    ) = saveTag("$activity.java_${targetId}_$eventName", list.joinToString("\n") {
        it.deserialize()
    })

    private fun removeBlock(name: String) {
        value = BeansParser.removeTag(name, value)
    }

    private fun saveVariables(activity: String, list: List<VariableModel>) =
        saveTag("$activity.java_var", list.joinToString("\n"))

    private fun saveListVariables(activity: String, list: List<ListVariableModel>) =
        saveTag("$activity.java_list", list.joinToString("\n"))

}