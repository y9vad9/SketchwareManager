package io.sketchware.`interface`

import io.sketchware.manager.projects.entities.SketchwareProject

interface ProjectsManager<Project : SketchwareProject> {
    /**
     * Gets projects.
     * @return List of [SketchwareProject].
     */
    suspend fun getProjects(): List<Project>

    /**
     * Checks next free id and returns it.
     * @param startId - Specific id from which function
     * will check next free id. By default it is 601.
     * @return next free for new project id.
     */
    suspend fun nextFreeId(startId: Int): Int

}