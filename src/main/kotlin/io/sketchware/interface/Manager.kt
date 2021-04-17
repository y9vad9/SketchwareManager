package io.sketchware.`interface`

import io.sketchware.manager.collection.CollectionsManager


interface Manager<ProjectsManager> {
    /**
     * Sketchware projects manager for this instance.
     * Responsible for sketchware projects.
     */
    val projectsManager: ProjectsManager

    /**
     * Collections container with all collection managers:
     */
    val collectionsManager: CollectionsManager
}