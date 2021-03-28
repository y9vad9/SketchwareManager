package io.sketchware.interfaces

import io.sketchware.interfaces.listeners.ActionFinishListener
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