package `fun`.kotlingang.sketchware.interfaces

import `fun`.kotlingang.sketchware.manager.collections.CollectionsManager


interface SketchwareManager<ProjectsManager> {
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