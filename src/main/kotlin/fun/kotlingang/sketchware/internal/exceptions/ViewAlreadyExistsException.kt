package `fun`.kotlingang.sketchware.internal.exceptions

class ViewAlreadyExistsException internal constructor(viewName: String, widgetName: String? = null) :
    Exception("ViewArgument with name $viewName "
        .plus(widgetName?.let { "and widget name $widgetName" } ?: "")
        .plus("already exists.")
    )