package io.sketchware.interfaces

import io.sketchware.manager.collections.CollectionsManager


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