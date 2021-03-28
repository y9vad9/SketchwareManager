package io.sketchware.manager

import io.sketchware.interfaces.Manager
import io.sketchware.manager.collections.CollectionsManager
import io.sketchware.manager.customs.SketchwareStudioCustomManager
import io.sketchware.manager.projects.SketchwareStudioProjectsManager
import java.io.File

class SketchwareStudioManager(
    sketchwareFolder: File
) : Manager<SketchwareStudioProjectsManager> {

    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))


    /**
     * Sketchware Studio Customs Manager. Responsible for sketchware studio customs (components, blocks, etc)
     */
    val customsManager = SketchwareStudioCustomManager(
        File(sketchwareFolder, "data/system"),
        File(sketchwareFolder, "data/settings.json"), File(sketchwareFolder, "resources/block/My Block")
    )

    /**
     * Sketchware projects manager for this instance.
     * Responsible for sketchware projects.
     */
    override val projectsManager = SketchwareStudioProjectsManager(sketchwareFolder)

    /**
     * Sketchware collections manager.
     * Responsible for sketchware collections.
     */
    override val collectionsManager = CollectionsManager(File(sketchwareFolder, "collections"))

}