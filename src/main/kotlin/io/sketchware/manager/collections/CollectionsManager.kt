package io.sketchware.manager.collections

import io.sketchware.manager.collections.managers.BlockCollectionManager
import io.sketchware.manager.collections.managers.FileCollectionManager
import io.sketchware.manager.collections.managers.MoreblockCollectionManager
import java.io.File

class CollectionsManager(private val rootFolder: File) {

    private var moreblocksManager: MoreblockCollectionManager? = null
    private var blocksManager: BlockCollectionManager? = null
    private var fontsManager: FileCollectionManager? = null
    private var soundsManager: FileCollectionManager? = null
    private var imagesManager: FileCollectionManager? = null

    /**
     * @return instance of [MoreblockCollectionManager].
     */
    suspend fun getMoreblocksManager(): MoreblockCollectionManager {
        moreblocksManager = moreblocksManager
            ?: MoreblockCollectionManager(File(rootFolder, "more_block/list"))
        return moreblocksManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [BlockCollectionManager].
     */
    suspend fun getBlocksManager(): BlockCollectionManager {
        blocksManager = blocksManager
            ?: BlockCollectionManager(File(rootFolder, "block/list"))
        return blocksManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionManager].
     */
    suspend fun getFontsManager(): FileCollectionManager {
        fontsManager = fontsManager
            ?: FileCollectionManager(File(rootFolder, "font/list"))
        return fontsManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionManager].
     */
    suspend fun getSoundsManager(): FileCollectionManager {
        soundsManager = soundsManager
            ?: FileCollectionManager(File(rootFolder, "sound/list"))
        return soundsManager ?: error("manager shouldn't initialized")
    }

    /**
     * @return instance of [FileCollectionManager].
     */
    suspend fun getImagesManager(): FileCollectionManager {
        imagesManager = imagesManager
            ?: FileCollectionManager(File(rootFolder, "image/list"))
        return imagesManager ?: error("manager shouldn't initialized")
    }

}