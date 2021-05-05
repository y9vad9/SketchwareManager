package `fun`.kotlingang.sketchware.interfaces.editors.content

import `fun`.kotlingang.sketchware.interfaces.editors.Editor
import `fun`.kotlingang.sketchware.objects.project.content.ActivityView
import `fun`.kotlingang.sketchware.objects.project.content.CustomView
import `fun`.kotlingang.sketchware.objects.project.content.DrawerView

interface ContentEditor : Editor {

    /**
     * @return [MutableList] of [ActivityView] with data about activities.
     * Can be changed and saved using [save].
     */
    val activities: MutableList<ActivityView>

    /**
     * @return [MutableList] of [CustomView] with data about custom views.
     */
    val customViews: MutableList<CustomView>

    /**
     * @return [MutableList] of [DrawerView] with data about drawers.
     */
    val drawerViews: MutableList<DrawerView>

}