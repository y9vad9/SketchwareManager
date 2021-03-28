package io.sketchware.exceptions

class ViewAlreadyExistsException(viewName: String, widgetName: String? = null) :
    Exception("View with name $viewName "
        .plus(widgetName?.let { "and widget name $widgetName" } ?: "")
        .plus("already exists.")
    )