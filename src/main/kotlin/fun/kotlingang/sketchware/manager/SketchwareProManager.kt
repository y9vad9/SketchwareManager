package `fun`.kotlingang.sketchware.manager

import `fun`.kotlingang.sketchware.interfaces.SketchwareManager
import `fun`.kotlingang.sketchware.manager.collections.CollectionsManager
import `fun`.kotlingang.sketchware.manager.customs.SWProCustomManager
import `fun`.kotlingang.sketchware.manager.projects.SWProProjectsManager
import java.io.File

class SketchwareProManager(
    sketchwareFolder: File
) : SketchwareManager<SWProProjectsManager> {
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
     * Sketchware Pro Customs SketchwareManager. Responsible for sketchware pro customs (components, blocks, etc)
     */
    val customsManager = SWProCustomManager(
        File(sketchwareFolder, "data/system"),
        File(sketchwareFolder, "data/settings.json"), File(sketchwareFolder, "resources/block/Menu Block")
    )

}