package io.sketchware

import io.sketchware.project.SketchwareProject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File


class JavaSketchwareProjects : CoroutineScope {

    /** May be used for non-suspend functions i.e. [SketchwareProjects.nextFreeId] */
    @Suppress("MemberVisibilityCanBePrivate")
    val manager: SketchwareProjects

    constructor(sketchcodeFolder: File) {
        manager = SketchwareProjects(sketchcodeFolder)
    }
    constructor(sketchcodeFolder: String) {
        manager = SketchwareProjects(sketchcodeFolder)
    }

    fun interface ProjectsLoadedCallback {
        fun onLoad(projects: List<SketchwareProject>?)
    }
    fun getProjects(callback: ProjectsLoadedCallback) = launch {
        callback.onLoad(manager.getProjects())
    }

    override val coroutineContext = GlobalScope.coroutineContext + Job()
}