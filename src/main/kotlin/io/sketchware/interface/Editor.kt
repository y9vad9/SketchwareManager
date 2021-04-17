package io.sketchware.`interface`

import io.sketchware.`interface`.listener.ActionFinishListener
import kotlinx.coroutines.Job

interface Editor {

    /**
     * Updates data in Editor async.
     */
    fun fetch(callback: ActionFinishListener? = null): Job

    /**
     * Updates data in Editor.
     */
    suspend fun fetch()

    /**
     * Saves data which was edited async.
     */
    fun save(callback: ActionFinishListener? = null): Job

    /**
     * Saves data which was edited.
     */
    suspend fun save()

}