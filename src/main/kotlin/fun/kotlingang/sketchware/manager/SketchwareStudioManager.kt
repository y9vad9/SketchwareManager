package `fun`.kotlingang.sketchware.manager

import `fun`.kotlingang.sketchware.interfaces.managers.SketchwareManager
import `fun`.kotlingang.sketchware.manager.collections.CollectionsManager
import `fun`.kotlingang.sketchware.manager.customs.SWStudioCustomManager
import `fun`.kotlingang.sketchware.manager.projects.SketchwareStudioProjectsManager
import java.io.File

class SketchwareStudioManager(
    sketchwareFolder: File
) : SketchwareManager<SketchwareStudioProjectsManager> {

    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))


    /**
     * Sketchware Studio Customs SketchwareManager. Responsible for sketchware studio customs (components, blocks, etc)
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