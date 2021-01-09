package io.sketchware.project

import io.sketchware.project.models.ProjectConfig
import io.sketchware.project.models.ProjectDestination
import kotlinx.serialization.Serializable
import java.io.File

@Serializable
abstract class Project {
    abstract var information: ProjectConfig
    abstract var infoFile: File

    /**
     * Clone of resource files, data files. The copy goes without garbage (mysc folders and bak)
     */
    abstract suspend fun edit(builder: ProjectConfig.() -> Unit)
    abstract suspend fun clone(id: Int, dest: ProjectDestination)
    abstract suspend fun getData()
    abstract suspend fun delete()
}