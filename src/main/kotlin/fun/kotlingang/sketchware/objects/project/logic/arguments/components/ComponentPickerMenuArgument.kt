package `fun`.kotlingang.sketchware.objects.project.logic.arguments.components

import `fun`.kotlingang.sketchware.interfaces.objects.ExpressibleArgument
import `fun`.kotlingang.sketchware.internal.extensions.nullIfBlank
import `fun`.kotlingang.sketchware.objects.project.logic.arguments.menu.MenuArgumentString
import `fun`.kotlingang.sketchware.objects.project.logic.blocks.expressions.components.ComponentExpressionBlock

sealed class ComponentPickerMenuArgument<CEB : ComponentExpressionBlock>(
    private var componentName: String?, private var expressionSource: CEB?
) : MenuArgumentString(componentName.nullIfBlank()), ExpressibleArgument<CEB> {
    override var value: String?
        get() = componentName
        set(value) {
            expressionSource = null
            componentName = value
        }

    override var expression: CEB?
        get() = expressionSource
        set(value) {
            componentName = null
            expressionSource = value
        }
}