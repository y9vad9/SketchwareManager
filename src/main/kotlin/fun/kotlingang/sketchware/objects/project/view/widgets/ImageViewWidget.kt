package `fun`.kotlingang.sketchware.objects.project.view.widgets

import `fun`.kotlingang.sketchware.objects.project.view.properties.WidgetProperties

class ImageViewWidget(view: WidgetProperties) : BaseWidget(view) {
    private val imageProperties get() = view.image

    /**
     * ImageView image drawable resource name.
     */
    var image by imageProperties::resName

    /**
     * ImageView image scale type.
     */
    var scaleType by imageProperties::scaleType

}