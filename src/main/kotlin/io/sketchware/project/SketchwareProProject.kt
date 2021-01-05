package io.sketchware.project

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.project.models.*
import io.sketchware.project.models.sketchwarepro.ProguardData
import io.sketchware.serialize
import io.sketchware.toJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

data class SketchwareProProject(
    override var infoFile: File,
    override var information: ProjectConfig,
    var resources: SketchwareProjectResources,
    var data: SketchwareProProjectData?,
    var mysc: File?,
    var backup: SketchwareProjectData?
) : Project() {

    override suspend fun edit(builder: ProjectConfig.() -> Unit) {
        information = information.apply(builder)
        infoFile.writeBytes(FileEncryptor.encrypt(information.toJson().toByteArray()))
    }

    suspend fun getProguard(): ProguardData? = withContext(Dispatchers.IO) {
        data?.proguard?.readText()?.serialize()
    }

    suspend fun setProguard(proguardData: ProguardData) = withContext(Dispatchers.IO) {
        data?.proguard?.writeText(proguardData.toJson())
    }

    suspend fun getProguardRules(): String? = withContext(Dispatchers.IO) {
        data?.proguardRules?.readText()
    }

    suspend fun setProguardRules(rules: String) = withContext(Dispatchers.IO) {
        data?.proguardRules?.writeBytes(rules.toByteArray())
    }

    override suspend fun clone(id: Int, dest: ProjectDestination): Unit = withContext(Dispatchers.IO) {
        infoFile.copyTo(dest.projectFile, overwrite = true)
        dest.projectFile.writeBytes(FileEncryptor.encrypt(information.copy(scId = "$id").toJson().toByteArray()))
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