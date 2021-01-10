package io.sketchware.collections

import io.sketchware.models.exceptions.SketchwareFolderError
import java.io.File

class SketchwareCollections(private val collectionsFolder: File) {
    constructor(collectionsPath: String) : this(File(collectionsPath))

    init {
        if (!collectionsFolder.isDirectory)
            throw SketchwareFolderError(collectionsFolder.path)
    }

    val moreblocksManager by lazy { SketchwareCollection(File(collectionsFolder, "more_block/list")) }
    val imagesManager by lazy { SketchwareCollection(File(collectionsFolder, "image/list")) }
    val widgetsManager by lazy { SketchwareCollection(File(collectionsFolder, "widget/list")) }
    val fontsManager by lazy { SketchwareCollection(File(collectionsFolder, "font/list")) }
    val soundsManager by lazy { SketchwareCollection(File(collectionsFolder, "sound/list")) }
    val blocksManager by lazy { SketchwareCollection(File(collectionsFolder, "block/list")) }
}