package io.sketchware

import io.sketchware.encryptor.FileEncryptor
import io.sketchware.exceptions.projects.SketchwareFolderError
import io.sketchware.project.Project
import io.sketchware.project.SketchwareProProject
import io.sketchware.project.SketchwareProject
import io.sketchware.project.models.*
import java.io.File

class SketchwareProjects(private val sketchwareFolder: File) {

    init {
        if (sketchwareFolder.isFile)
            throw SketchwareFolderError(sketchwareFolder.path)
    }

    constructor(sketchwareFolder: String) : this(File(sketchwareFolder))

    suspend fun getProjects(): List<Project>? {
        return File(File(sketchwareFolder, "mysc"), "list").getListFiles()?.map { projectFolder ->

            val projectConfig = FileEncryptor.decrypt(
                File(projectFolder, "project")
                    .readFile()
            ).serialize<ProjectConfig>()

            val projectResources = getResources(projectConfig.scId.toInt())

            when (val projectData = getData(projectConfig.scId.toInt())) {
                is SketchwareProjectData -> SketchwareProject(
                    File(projectFolder, "project"), projectConfig, projectResources, projectData,
                    File(File(sketchwareFolder, "mysc"), projectConfig.scId),
                    getBak(projectConfig.scId.toInt())
                )
                is SketchwareProProjectData -> SketchwareProProject(
                    File(projectFolder, "project"), projectConfig, projectResources, projectData,
                    File(File(sketchwareFolder, "mysc"), projectConfig.scId),
                    getBak(projectConfig.scId.toInt())
                )
                else -> TODO("Not implemented type of project")
            }
        }
    }

    fun getResources(id: Int): SketchwareProjectResources {
        val resourcesFolder = File(sketchwareFolder, "resources")
        val iconsFolder = File(File(resourcesFolder, "icons"), "$id")
        val imagesFolder = File(File(resourcesFolder, "images"), "$id")
        val fontsFolder = File(File(resourcesFolder, "fonts"), "$id")
        val soundsFolder = File(File(resourcesFolder, "sounds"), "$id")
        return SketchwareProjectResources(imagesFolder, iconsFolder, fontsFolder, soundsFolder)
    }

    fun getData(id: Int): ProjectData {
        val projectDataFolder = File(File(sketchwareFolder, "data"), id.toString())
        val projectFile = File(projectDataFolder, "file")
        val projectLibrary = File(projectDataFolder, "library")
        val projectLogic = File(projectDataFolder, "logic")
        val projectResource = File(projectDataFolder, "resource")
        val projectView = File(projectDataFolder, "view")
        val projectConfig = File(projectDataFolder, "project_config")
        return if (projectConfig.exists()) {
            val projectProguard = File(projectDataFolder, "proguard")
            val projectProguardRules = File(projectDataFolder, "proguard-rules.pro")
            val projectStringFog = File(projectDataFolder, "stringfog")
            SketchwareProProjectData(
                projectDataFolder, projectFile, projectLibrary, projectLogic, projectResource,
                projectView, projectProguard, projectProguardRules, projectStringFog, projectConfig
            )
        } else SketchwareProjectData(
            projectDataFolder, projectFile, projectLibrary,
            projectLogic, projectResource, projectView
        )
    }

    fun getBak(id: Int): SketchwareProjectData? {
        val projectBakFolder = File(File(sketchwareFolder, "bak"), id.toString())
        if (!projectBakFolder.exists()) return null

        val projectFile = File(projectBakFolder, "file")
        val projectLibrary = File(projectBakFolder, "library")
        val projectLogic = File(projectBakFolder, "logic")
        val projectResource = File(projectBakFolder, "resource")
        val projectView = File(projectBakFolder, "view")
        return SketchwareProjectData(
            projectBakFolder, projectFile, projectLibrary,
            projectLogic, projectResource, projectView
        )
    }

    val nextFreeId: Int get() = getFreeId(601)

    private fun getFreeId(startId: Int): Int {
        File(File(sketchwareFolder, "mysc"), "list").listFiles()!!.forEach {
            if (it.name == startId.toString())
                return getFreeId(startId + 1)
        }
        return startId
    }

}