package io.sketchware.utils.internal

import io.sketchware.manager.customs.SketchwareProCustomManager
import io.sketchware.manager.projects.entities.SketchwareProProject
import io.sketchware.models.customs.*
import io.sketchware.models.projects.MenuSpecArgument
import io.sketchware.utils.LogicOpCodes

// shit code day is announced open. DON'T LOOK AT THE CODE WHICH GOES NEXT. PLEASE.
internal class ProjectCustomExporter(
    val project: SketchwareProProject,
    val customsManager: SketchwareProCustomManager
) {
    private suspend fun getComponentsIdsToExport() = project.getFileManager().activities.map { activity ->
        val componentsId = mutableListOf<Int>()
        project.getLogicManager().getComponents(activity.activityName)?.forEach {
            if (it.type !in 0..18)
                componentsId.add(it.type)
        }
        return@map componentsId
    }.flatten()

    private suspend fun getBlocksOpCodesToExport() = project.getFileManager().activities.map { activity ->
        val blockOpCodes = mutableListOf<String>()
        project.getLogicManager().getEvents(activity.activityName)?.forEach {
            project.getLogicManager().getEventLogic(activity.activityName, it.targetId, it.name)
                ?.forEach { block ->
                    if (!LogicOpCodes.getOpCodes().contains(block.opCode))
                        blockOpCodes.add(block.opCode)
                }
        }
        project.getLogicManager().getMoreblocks(activity.activityName)?.forEach { moreblock ->
            project.getLogicManager().getMoreblockLogic(activity.activityName, moreblock.name)
                ?.forEach { block ->
                    if (!LogicOpCodes.getOpCodes().contains(block.opCode))
                        blockOpCodes.add(block.opCode)
                }
        }
        return@map blockOpCodes
    }.flatten()

    private suspend fun getMenusNamesToExport() = project.getFileManager().activities.map { activity ->
        val menuNames = mutableListOf<String>()
        project.getLogicManager().getEvents(activity.activityName)?.forEach { event ->
            project.getLogicManager().getEventLogic(activity.activityName, event.targetId, event.name)
                ?.forEach { block ->
                    block.spec.filterIsInstance<MenuSpecArgument>().forEach {
                        try {
                            it.menuArgumentType
                        } catch (e: Exception) {
                            menuNames.add(it.menuArgumentTypeName)
                        }
                    }
                }
        }
        project.getLogicManager().getMoreblocks(activity.activityName)?.forEach { moreblock ->
            project.getLogicManager().getMoreblockLogic(activity.activityName, moreblock.name)?.forEach { block ->
                block.spec.filterIsInstance<MenuSpecArgument>().forEach {
                    try {
                        it.menuArgumentType
                    } catch (e: Exception) {
                        menuNames.add(it.menuArgumentTypeName)
                    }
                }
            }
        }
        return@map menuNames
    }.flatten()

    private suspend fun getListenersToExport() = project.getFileManager().activities.map { activity ->
        val eventsToExport = mutableListOf<String>()
        project.getLogicManager().getEvents(activity.activityName)?.forEach { event ->
            if (event.type == 3)
                eventsToExport.add(event.name)
        }
        return@map eventsToExport
    }.flatten()

    suspend fun getComponentsToExport(): List<CustomComponent> {
        val componentsIds = getComponentsIdsToExport()
        val components = customsManager.getComponentManager().components
        return components.filter {
            componentsIds.contains(it.id)
        }
    }

    suspend fun getBlocksToExport(): List<CustomBlockGroup> {
        val blocksOpCodes = getBlocksOpCodesToExport()
        val customBlocks = customsManager.getBlocksManager().blocks
        return customBlocks.filter { blockGroup ->
            blockGroup.blocks.any { blocksOpCodes.contains(it.typeName) }
        }
    }

    suspend fun getMenusToExport(): List<CustomMenu> {
        val menuNames = getMenusNamesToExport()
        val menus = customsManager.getMenusManager().menus
        return menus.filter {
            menuNames.contains(it.id)
        }
    }

    suspend fun getListenersGroupsToExport(): List<CustomListenerGroup> {
        val eventsToExport = getListenersToExport()
        val allEvents = customsManager.getListenersManager().listeners
        return allEvents.filter { event ->
            event.events.any { eventsToExport.contains(it.name) }
        }
    }

}