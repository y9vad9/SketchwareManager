package io.sketchware.model.project.widget

import io.sketchware.model.project.view.WidgetProperties

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