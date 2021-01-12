package io.sketchware.project.data.logic

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.utils.BlockParser
import io.sketchware.utils.SketchwareDataParser
import io.sketchware.models.sketchware.SketchwareProjectBlock
import io.sketchware.models.sketchware.data.*
import io.sketchware.utils.readFile
import io.sketchware.utils.replaceOrInsertAtTop
import io.sketchware.utils.toModel
import io.sketchware.utils.writeFile
import java.io.File

open class SketchwareProjectLogicManager(private val file: File) {
    private var list: List<BlockDataModel>? = null
    private var decryptedString: String? = null

    private suspend fun getList(): List<BlockDataModel> {
        if (list == null)
            list = SketchwareDataParser.parseJsonBlocks(getDecryptedString())
        return list ?: throw error("list shouldn't be null")
    }

    private suspend fun getDecryptedString(): String {
        if (decryptedString == null)
            decryptedString = String(FileEncryptor.decrypt(file.readFile()))
        return decryptedString ?: throw error("list shouldn't be null")
    }

    /**
     * Get activity events
     * @param activity activity name (Example: MainActivity)
     */
    suspend fun getEvents(activity: String): List<SketchwareEvent>? {
        return getList().find {
            it.name == "$activity.java_events"
        }?.values?.map { it.toModel() }
    }

    /**
     * Get activity moreblocks
     * @param activity activity name (Example: MainActivity)
     */
    suspend fun getMoreblocks(activity: String): List<SketchwareProjectMoreblock>? {
        val regex = Regex("(?:@$activity\\.java_func(.*?))(?=@|\$)", RegexOption.DOT_MATCHES_ALL)
        return regex.find(getDecryptedString())?.groupValues?.get(1)?.let {
            SketchwareDataParser.parseTextBlocks(
                it
            )
        }?.map { (first, second) -> SketchwareProjectMoreblock(first, second) }
    }

    /**
     * Get components in specific activity.
     * @param activity activity name (Example: MainActivity)
     * @return List of SketchwareComponent.
     */
    suspend fun getComponents(activity: String): List<SketchwareComponent>? {
        return getList().find {
            it.name == "$activity.java_components"
        }?.values?.map { it.toModel() }
    }

    /**
     * Get variables in specific activity.
     * @param activity Activity name (example: MainActivity)
     */
    suspend fun getVariables(activity: String): List<SketchwareVariable>? {
        return Regex("(?:@$activity\\.java_var(.*?))(?=@|\$)", RegexOption.DOT_MATCHES_ALL)
            .find(getDecryptedString())
            ?.groupValues
            ?.get(1)
            ?.let { SketchwareDataParser.parseTextBlocks(it) }
            ?.map { (type, name) -> SketchwareVariable(name, type.toInt()) }
    }

    /**
     * Get logic of moreblock.
     * @return blocks in moreblock.
     */
    suspend fun getMoreblockLogic(activity: String, name: String): List<SketchwareProjectBlock>? {
        return getList().find {
            it.name == "$activity.java_${name}_moreBlock"
        }?.values?.map { it.toModel() }
    }

    /**
     * Get logic of event.
     * @return blocks in event.
     */
    suspend fun getEventLogic(activity: String, targetId: String, eventName: String): List<SketchwareProjectBlock>? {
        return getList().find {
            it.name == "$activity.java_${targetId}_$eventName"
        }?.values?.map { it.toModel() }
    }

    /**
     * Get blocks in onCreate. Sketchware doesn't mark it as event (wtf xdd),
     * that's why there is additional method.
     * @return blocks in onCreate
     */
    suspend fun getOnCreateLogic(activity: String): List<SketchwareProjectBlock>? {
        return getList().find {
            it.name == "$activity.java_onCreate_initializeLogic"
        }?.values?.map { it.toModel() }
    }

    /**
     * Edit onCreate event.
     * @param activity Activity Name (example: MainActivity)
     */
    suspend fun editOnCreateLogic(
        activity: String,
        builder: ArrayList<SketchwareProjectBlock>.() -> Unit
    ) = editLogic(activity, "onCreate_initializeLogic", builder)

    /**
     * Removes event by event name and target id.
     * @param activity Activity Name (example: MainActivity)
     * @param eventName name of event
     * @param targetId Indicates the name of the widget.
     * @return true or false in depends on removing status (if isn't something changed, it returns false).
     */
    suspend fun removeEvent(activity: String, eventName: String, targetId: String): Boolean {
        val events = ArrayList(getEvents(activity) ?: return false)
        if (!events.removeIf
            { it.name == eventName && it.targetId == targetId }
        ) return false

        removeLogic(activity, "${targetId}_$eventName")
        saveEvents(activity, events)
        return true
    }

    /**
     * Save events. It will replace already exist events.
     * @param activity Activity Name (example: MainActivity)
     * @param list list of events
     */
    private suspend fun saveEvents(activity: String, list: List<SketchwareEvent>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$activity\\.java_events.*?)(?=@|\$)".toRegex(),
            "@$activity.java_events${BlockParser.toSaveableValue(list)}\n\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        decryptedString = null
    }

    /**
     * Edit logic of some event.
     * @param activity Activity Name (example: MainActivity).
     * @param eventName Event Name (example: onClick).
     * @param targetId Target id (example: button1).
     */
    suspend fun editEventLogic(
        activity: String,
        eventName: String,
        targetId: String,
        builder: ArrayList<SketchwareProjectBlock>.() -> Unit
    ) = editLogic(activity, "${targetId}_$eventName", builder)

    /**
     * Add event to project activity.
     * @param activity Activity name (example: MainActivity)
     * @param event Sketchware Event, be careful with adding
     */
    suspend fun addEvent(activity: String, event: SketchwareEvent, blocks: List<SketchwareProjectBlock>) {
        val array = ArrayList(getEvents(activity) ?: ArrayList())
        array.add(event)
        saveEvents(activity, array)
        saveEventLogic(activity, event.name, event.targetId, blocks)
    }

    /**
     * Save components in readable for sketchware look.
     * @param activity Activity name (example: MainActivity)
     * @param list list of components to save
     */
    private suspend fun saveComponents(activity: String, list: List<SketchwareComponent>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$activity\\.java_components.*?)(?=@|\$)".toRegex(),
            "@$activity.java_components${BlockParser.toSaveableValue(list)}\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        decryptedString = null
    }

    /**
     * Removes component by component id.
     * @param activity Activity Name (example: MainActivity)
     * @param componentId Component id.
     * @return true or false in depends on removing status (if isn't something changed, it returns false).
     */
    suspend fun removeComponent(activity: String, componentId: String): Boolean {
        val components = ArrayList(getComponents(activity) ?: return false)
        if (!components.removeIf { it.id == componentId }) return false
        saveComponents(activity, components)
        return true
    }

    /**
     * Add component to project activity.
     * @param activity Activity name (example: MainActivity)
     * @param component Sketchware Component
     */
    suspend fun addComponent(activity: String, component: SketchwareComponent) {
        val components = ArrayList(getComponents(activity) ?: ArrayList())
        components.add(component)
        saveComponents(activity, components)
    }

    /**
     * Save variables in readable for sketchware look.
     * @param activity Activity name (example: MainActivity)
     * @param list list of variables
     */
    private suspend fun saveVariables(activity: String, list: List<SketchwareVariable>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$activity\\.java_var.*?)(?=@|\$)".toRegex(),
            "@$activity.java_var${list.joinToString("\n")}\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        decryptedString = null
    }

    private suspend fun saveMoreblocks(activity: String, list: List<SketchwareProjectMoreblock>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$activity\\.java_func.*?)(?=@|\$)".toRegex(),
            "@$activity.java_func${list.joinToString("\n")}\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        this.decryptedString = null
    }

    /**
     * Add variable to specific activity.
     * @param activity Activity Name (example: MainActivity)
     * @param variable SketchwareVariable instance which contains data about variable
     */
    suspend fun addVariable(activity: String, variable: SketchwareVariable) {
        val variables = ArrayList(getVariables(activity) ?: ArrayList())
        variables.add(variable)
        saveVariables(activity, variables)
    }

    /**
     * Remove variable.
     * @param activity Activity Name (example: MainActivity)
     * @param variable variable to delete.
     * @return delete status. If nothing deleted returns false.
     */
    suspend fun removeVariable(activity: String, variable: SketchwareVariable): Boolean {
        val variables = ArrayList(getVariables(activity) ?: return false)
        return variables.remove(variable).also { if (it) saveVariables(activity, variables) }
    }

    /**
     * Add moreblock to specific activity.
     * @param activity Activity Name (example: MainActivity)
     * @param moreblock SketchwareMoreblock Model with data about moreblock.
     * @param contentBlocks blocks in this moreblock.
     */
    suspend fun addMoreblock(
        activity: String,
        moreblock: SketchwareProjectMoreblock,
        contentBlocks: List<SketchwareProjectBlock>
    ) {
        val moreblocks = ArrayList(getMoreblocks(activity) ?: ArrayList())
        moreblocks.add(moreblock)
        saveMoreblocks(activity, moreblocks)
        saveMoreblockLogic(activity, moreblock.name, contentBlocks)
    }

    private suspend fun saveMoreblockLogic(activity: String, name: String, list: List<SketchwareProjectBlock>) =
        saveLogic(activity, "$activity.java_${name}_moreBlock", list)

    private suspend fun saveEventLogic(
        activity: String,
        eventName: String,
        targetId: String,
        list: List<SketchwareProjectBlock>
    ) = saveLogic(activity, "${targetId}_$eventName", list)

    /**
     * Removes moreblock from the logic in specific activity.
     * @param activity Activity Name (example: MainActivity)
     * @param moreblock moreblock model with data about moreblock.
     */
    suspend fun removeMoreblock(activity: String, moreblock: SketchwareProjectMoreblock): Boolean {
        val moreblocks = ArrayList(getMoreblocks(activity) ?: return false)
        removeLogic(activity, "${moreblock.name}_moreBlock")
        return moreblocks.remove(moreblock).also { if (it) saveMoreblocks(activity, moreblocks) }
    }

    private suspend fun saveLogic(activity: String, name: String, list: List<SketchwareProjectBlock>) {
        decryptedString = getDecryptedString().replaceOrInsertAtTop(
            "(@$activity\\.java_$name.*?)(?=@|\$)".toRegex(),
            if (list.isEmpty())
                ""
            else "@$activity.java_$name${BlockParser.toSaveableValue(list)}\n\n"
        )
        file.writeFile(FileEncryptor.encrypt(getDecryptedString().toByteArray()))
        this.list = null
        decryptedString = null
    }

    private suspend fun editLogic(
        activity: String,
        name: String,
        builder: ArrayList<SketchwareProjectBlock>.() -> Unit
    ) {
        val list = ArrayList(
            getList().find {
                it.name == "$activity.java_$name"
            }?.values?.map { it.toModel<SketchwareProjectBlock>() }!!
        )
        saveLogic(activity, name, list.apply(builder))
    }

    /**
     * Edits moreblock logic.
     * @param activity Activity Name (example: MainActivity)
     * @param name Name of Moreblock
     */
    suspend fun editMoreblockLogic(
        activity: String,
        name: String,
        builder: ArrayList<SketchwareProjectBlock>.() -> Unit
    ) = editLogic(activity, "${name}_moreBlock", builder)

    private suspend fun removeLogic(activity: String, name: String) {
        decryptedString = Regex(
            "(?<=@)${activity}\\.java_$name.*?(?=\\n@|\$)", RegexOption.DOT_MATCHES_ALL
        ).replace(getDecryptedString(), "\n")
    }

}