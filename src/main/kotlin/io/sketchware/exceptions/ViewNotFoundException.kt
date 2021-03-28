package io.sketchware.exceptions

class ViewNotFoundException(
    private val filePath: String,
    private val viewName: String,
    private val widget: String? = null
) : Exception() {
    override val message: String
        get() {
            return if (widget != null)
                "Widget $widget not found in view $viewName at path $filePath."
            else "View $viewName not found at path $filePath"
        }
}