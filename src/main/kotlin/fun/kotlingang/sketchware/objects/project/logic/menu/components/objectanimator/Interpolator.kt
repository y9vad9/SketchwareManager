package `fun`.kotlingang.sketchware.objects.project.logic.menu.components.objectanimator

import kotlinx.serialization.Serializable

@Serializable
enum class Interpolator {
    Linear, Accelerate, Decelerate, AccelerateDecelerate, Bounce
}