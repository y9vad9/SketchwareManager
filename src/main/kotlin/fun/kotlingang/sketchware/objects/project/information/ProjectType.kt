package `fun`.kotlingang.sketchware.objects.project.information

import `fun`.kotlingang.sketchware.interfaces.objects.Identifiable

enum class ProjectType(override val id: Int) : Identifiable {
    SKETCHWARE(1), SKETCHWARE_PRO(2), SKETCHWARE_STUDIO(3)
}