package `fun`.kotlingang.sketchware.manager.collections

import `fun`.kotlingang.sketchware.editors.collections.BlockCollectionEditor
import `fun`.kotlingang.sketchware.editors.collections.FileCollectionEditor
import `fun`.kotlingang.sketchware.editors.collections.MoreblockCollectionEditor
import java.io.File

class CollectionsManager(private val rootFolder: File) {

    private var moreblocksManager: MoreblockCollectionEditor? = null
    private var blocksEditor: BlockCollectionEditor? = null
    private var fontsManager: FileCollectionEditor? = null
    private var soundsManager: FileCollectionEditor? = null
    private var imagesManager: FileCollectionEditor? = null

    /**
     * @return instance of [MoreblockCollectionEditor].
     */
    suspend fun getMoreblocksManager(): MoreblockCollectionEditor {
        moreblocksManager = moreblocksManager
            ?: MoreblockCollectionEditor(File(rootFolder, "more_block/list"))
        return moreblocksManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [BlockCollectionEditor].
     */
    suspend fun getBlocksManager(): BlockCollectionEditor {
        blocksEditor = blocksEditor
            ?: BlockCollectionEditor(File(rootFolder, "block/list"))
        return blocksEditor ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionEditor].
     */
    suspend fun getFontsManager(): FileCollectionEditor {
        fontsManager = fontsManager
            ?: FileCollectionEditor(File(rootFolder, "font/list"))
        return fontsManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionEditor].
     */
    suspend fun getSoundsManager(): FileCollectionEditor {
        soundsManager = soundsManager
            ?: FileCollectionEditor(File(rootFolder, "sound/list"))
        return soundsManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionEditor].
     */
    suspend fun getImagesManager(): FileCollectionEditor {
        imagesManager = imagesManager
            ?: FileCollectionEditor(File(rootFolder, "image/list"))
        return imagesManager ?: error("manager shouldn't initialized")
    }

}