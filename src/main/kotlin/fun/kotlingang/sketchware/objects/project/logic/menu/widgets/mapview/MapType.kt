package `fun`.kotlingang.sketchware.objects.project.logic.menu.widgets.mapview

import kotlinx.serialization.Serializable

@Serializable
enum class MapType {
    MAP_TYPE_NONE, MAP_TYPE_NORMAL, MAP_TYPE_SATELLITE, MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID
}