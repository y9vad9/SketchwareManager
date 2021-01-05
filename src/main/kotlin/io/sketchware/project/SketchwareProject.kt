package io.sketchware.project

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.project.models.ProjectConfig
import io.sketchware.project.models.ProjectDestination
import io.sketchware.project.models.SketchwareProjectData
import io.sketchware.project.models.SketchwareProjectResources
import io.sketchware.toJson
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

    override suspend fun clone(id: Int, dest: ProjectDestination): Unit = withContext(Dispatchers.IO) {
        dest.projectFile.parentFile.mkdirs()
        infoFile.copyTo(dest.projectFile, overwrite = true)
        dest.projectResources.apply {
            sounds?.let { soundsDest -> resources.sounds?.copyRecursively(soundsDest) }
            icons?.let { iconsDest -> resources.icons?.copyRecursively(iconsDest) }
            images?.let { imagesDest -> resources.images?.copyRecursively(imagesDest) }
            fonts?.let { fontsDest -> resources.fonts?.copyRecursively(fontsDest) }
        }
        data?.folder?.copyRecursively(dest.projectDataFolder)
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