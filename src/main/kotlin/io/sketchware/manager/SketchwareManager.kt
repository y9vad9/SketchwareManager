package io.sketchware.manager

import io.sketchware.`interface`.Manager
import io.sketchware.manager.collection.CollectionsManager
import io.sketchware.manager.projects.SWProjectsManager
import java.io.File

/**
 * Automatically determines what type of Manager
 * should be used to work with Sketchware (i.e. it determines whether it is a mod or not).
 * Attention: the function does not guarantee that Sketchware can be a mod or not,
 * the function checks if there are files created by
 * Sketchware Pro / Studio mods (later it will be specified what type of Sketchware) files.
 * If you can, avoid this feature as it can be very time consuming.
 *
 * @param sketchwareFolder - Sketchware root folder.
 */
//suspend fun sketchwareManager(sketchwareFolder: File): Nothing = TODO()

/**
 * Basic class for access with sketchware.
 * @param sketchwareFolder - basic sketchware folder (example: /storage/emulated/0/.sketchware).
 */
open class SketchwareManager(private val sketchwareFolder: File) : Manager<SWProjectsManager> {
    /**
     * @param folderPath - path to sketchware folder.
     */
    constructor(folderPath: String) : this(File(folderPath))

    /**
     * Sketchware projects manager for this instance.
     * Responsible for sketchware projects.
     */
    override val projectsManager = SWProjectsManager(sketchwareFolder)

    /**
     * The manager responsible for the Sketchware collections.
     */
    override val collectionsManager = CollectionsManager(File(sketchwareFolder, "collections"))

}