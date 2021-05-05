package `fun`.kotlingang.sketchware.objects.project.logic.menu.components.objectanimator

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TargetProperty {
    @SerialName("rotation") ROTATION,
    @SerialName("transactionX") TRANSACTION_X,
    @SerialName("transactionY") TRANSACTION_Y,
    @SerialName("alpha") ALPHA,
    @SerialName("scaleX") SCALE_X,
    @SerialName("scaleY") SCALE_Y
}