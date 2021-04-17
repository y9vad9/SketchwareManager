package io.sketchware.`interface`

import io.sketchware.`interface`.listener.ActionFinishListener
import kotlinx.coroutines.Job
import java.io.File

interface FileExportable {
    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     */
    suspend fun export(destination: File)

    /**
     * Exports data into file.
     * @param destination - file to which will be written data.
     * @param callback - call back when export will be finished.
     */
    fun export(destination: File, callback: ActionFinishListener): Job
}