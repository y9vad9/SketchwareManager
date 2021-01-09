package io.sketchware.project

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.project.models.ProjectConfig
import io.sketchware.project.models.ProjectDestination
import io.sketchware.project.models.SketchwareProjectData
import io.sketchware.project.models.SketchwareProjectResources
import io.sketchware.utils.copyFolder
import io.sketchware.utils.toJson
import io.sketchware.utils.writeFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

data class SketchwareProject(
    override var infoFile: File,
    override var information: ProjectConfig,
    var resources: SketchwareProjectResources,
    var data: SketchwareProjectData?,
    var mysc: File?,
    var backup: SketchwareProjectData?
) : Project() {

    override suspend fun edit(builder: ProjectConfig.() -> Unit) = withContext(Dispatchers.Default) {
        information = information.apply(builder)
        infoFile.writeBytes(FileEncryptor.encrypt(information.toJson().toByteArray()))
    }

    override suspend fun clone(id: Int, dest: ProjectDestination) {
        dest.projectFile.parentFile.mkdirs()
        dest.projectFile.writeFile(
            FileEncryptor.encrypt(information.copy(scId = "$id").toJson().toByteArray())
        )
        dest.projectResources.apply {
            sounds?.let { soundsDest -> resources.sounds?.copyFolder(soundsDest) }
            icons?.let { iconsDest -> resources.icons?.copyFolder(iconsDest) }
            images?.let { imagesDest -> resources.images?.copyFolder(imagesDest) }
            fonts?.let { fontsDest -> resources.fonts?.copyFolder(fontsDest) }
        }
        data?.folder?.copyFolder(dest.projectDataFolder)
    }

    override suspend fun getData() {
        TODO("Not yet implemented")
    }

    override suspend fun delete(): Unit = withContext(Dispatchers.IO) {
        infoFile.parentFile.deleteRecursively()
        resources.apply {
            fonts?.deleteRecursively()
            icons?.deleteRecursively()
            images?.deleteRecursively()
            sounds?.deleteRecursively()
        }
        data?.folder?.deleteRecursively()
        mysc?.deleteRecursively()
        backup?.folder?.deleteRecursively()
    }

}