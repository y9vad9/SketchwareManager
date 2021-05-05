package `fun`.kotlingang.sketchware.objects.project.logic.arguments.structured

import `fun`.kotlingang.sketchware.interfaces.objects.ExpressibleArgument
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.structured.MapExpressionBlock

class MapExpressibleArgument(
    private var variableNameSource: String?,
    private var mapExpressionBlockSource: MapExpressionBlock?
) : MapVariableArgument(variableNameSource), ExpressibleArgument<MapExpressionBlock> {

    override var variableName: String?
        get() = variableNameSource
        set(value) {
            variableNameSource = value
            mapExpressionBlockSource = null
        }

    override var expression: MapExpressionBlock?
        get() = mapExpressionBlockSource
        set(value) {
            variableNameSource = null
            mapExpressionBlockSource
        }

}