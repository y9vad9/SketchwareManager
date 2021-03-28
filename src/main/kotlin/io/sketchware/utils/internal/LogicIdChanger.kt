package io.sketchware.utils.internal

import io.sketchware.annotations.ExperimentalSWManagerAPI
import io.sketchware.manager.projects.data.LogicManager
import io.sketchware.models.projects.MenuSpecArgument
import io.sketchware.models.projects.SketchwareDataFileModel

internal class LogicIdChanger(
    private val logicManager: LogicManager,
    private val activities: List<SketchwareDataFileModel>
) {
    fun editComponentIds(list: List<Pair<Int, Int>>) = activities.forEach { file ->
        val activityName = "${file.fileName.capitalize()}Activity"
        logicManager.getComponents(activityName)?.filter { component ->
            list.any { it.first == component.type }
        }?.forEach { component ->
            logicManager.removeComponent(activityName, component.id)
            logicManager.addComponent(
                activityName, component.copy(type = list.first { it.first == component.type }.second)
            )
        }
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    fun editEventsType(list: List<Pair<String, String>>) = activities.forEach { activity ->
        logicManager.getEvents(activity.activityName)?.filter { event ->
            list.any { it.first == event.name }
        }?.forEach { event ->
            logicManager.editEventInfo(activity.activityName, event.targetId, event.name) {
                name = list.first { it.first == name }.second
            }
        }
    }

    fun editMenuNames(list: List<Pair<String, String>>) = activities.forEach { activity ->
        logicManager.getEvents(activity.activityName)?.forEach { event ->
            logicManager.editEventLogic(activity.activityName, event.targetId, event.name) { blocks ->
                blocks.forEach { block ->
                    block.spec.filterIsInstance<MenuSpecArgument>().forEach { menu ->
                        list.firstOrNull { it.first == menu.menuArgumentTypeName }?.let {
                            menu.menuArgumentTypeName = it.second
                        }
                    }
                }
            }
        }
    }

    suspend fun saveAll() {
        logicManager.save()
    }

}