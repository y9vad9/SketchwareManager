package io.sketchware.manager

import io.sketchware.`interface`.Manager
import io.sketchware.manager.collection.CollectionsManager
import io.sketchware.manager.custom.SWProCustomManager
import io.sketchware.manager.project.SWProProjectsManager
import java.io.File

class SketchwareProManager(
    sketchwareFolder: File
) : Manager<SWProProjectsManager> {
    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))

    /**
     * Sketchware projects manager for this instance.
     * Responsible for sketchware projects.
     */
    override val projectsManager = SWProProjectsManager(sketchwareFolder)

    /**
     * Sketchware collections manager.
     * Responsible for sketchware collections.
     */
    override val collectionsManager = CollectionsManager(File(sketchwareFolder, "collections"))

    /**
     * Sketchware Pro Customs Manager. Responsible for sketchware pro customs (components, blocks, etc)
     */
    val customsManager = SWProCustomManager(
        File(sketchwareFolder, "data/system"),
        File(sketchwareFolder, "data/settings.json"), File(sketchwareFolder, "resources/block/Menu Block")
    )

}