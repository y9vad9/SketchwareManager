package io.sketchware.`interface`

import io.sketchware.`interface`.listener.ActionFinishListener
import kotlinx.coroutines.Job
import java.io.File

interface FileImportable {
    /**
     * Imports data from [file].
     * @param file - File with data to import.
     */
    suspend fun import(file: File)

    /**
     * Imports data from [file].
     * @param file - File with data to import.
     * @param callback - call back when import will be finished.
     */
    fun import(file: File, callback: ActionFinishListener): Job
}