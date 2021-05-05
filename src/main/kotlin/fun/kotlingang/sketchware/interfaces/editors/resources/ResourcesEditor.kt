package `fun`.kotlingang.sketchware.interfaces.editors.resources

import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.objects.project.resources.ProjectResource

/**
 * Works with resources in specific project.
 */
interface ResourcesEditor : Editor {

    /**
     * @return [MutableList] of [ProjectResource] with images.
     */
    val images: MutableList<ProjectResource>

    /**
     * @return [MutableList] of [ProjectResource] with fonts.
     */
    val fonts: MutableList<ProjectResource>

    /**
     * @return [MutableList] of [ProjectResource] with sounds.
     */
    val sounds: MutableList<ProjectResource>

}