package io.sketchware.collections

import io.sketchware.exceptions.projects.SketchwareFolderError
import java.io.File

class SketchwareCollections(private val collectionsFolder: File) {
    constructor(collectionsPath: String) : this(File(collectionsPath))

    init {
        if (!collectionsFolder.isDirectory)
            throw SketchwareFolderError(collectionsFolder.path)
    }

    val moreblocks by lazy { SketchwareCollection(File(collectionsFolder, "more_block/list")) }
    val images by lazy { SketchwareCollection(File(collectionsFolder, "image/list")) }
    val widgets by lazy { SketchwareCollection(File(collectionsFolder, "widget/list")) }
    val fonts by lazy { SketchwareCollection(File(collectionsFolder, "font/list")) }
    val sounds by lazy { SketchwareCollection(File(collectionsFolder, "sound/list")) }
    val blocks by lazy { SketchwareCollection(File(collectionsFolder, "block/list")) }
}