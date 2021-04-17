package io.sketchware.manager

import io.sketchware.`interface`.Manager
import io.sketchware.manager.collection.CollectionsManager
import io.sketchware.manager.custom.SWStudioCustomManager
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
    val customsManager = SWStudioCustomManager(
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