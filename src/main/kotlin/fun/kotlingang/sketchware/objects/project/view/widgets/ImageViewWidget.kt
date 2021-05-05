package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ImageViewWidget(view: WidgetProperties = WidgetProperties()) : BaseWidget(view) {
    private val imageProperties get() = view.image

    /**
     * ImageView Argument image drawable resource name.
     */
    var image by imageProperties::resName

    /**
     * ImageView Argument image scale type.
     */
    var scaleType by imageProperties::scaleType

}

