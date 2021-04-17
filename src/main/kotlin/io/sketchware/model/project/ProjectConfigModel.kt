package io.sketchware.model.project

import io.sketchware.util.serializer.StringNumberConvertor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class with data about the project name, id, settings for the theme, etc.
 * It serializes usually from ../.sketchware/mysc/list/[projectId]/project
 */
@Serializable
data class ProjectConfigModel(
    /**
     * The version of Sketchware on which the project was last modified.
     */
    @SerialName("sketchware_ver")
    var sketchwareVersion: Double,
    /**
     * Unique digital identifier for the project.
     */
    @SerialName("sc_id")
    @Serializable(StringNumberConvertor::class)
    var projectId: Int,
    /**
     * Project's package name (example: com.android.test)
     */
    @SerialName("my_sc_pkg_name")
    var packageName: String,
    /**
     * Project's app name
     */
    @SerialName("my_app_name")
    var appName: String,
    /**
     * Project's app version code (Used to indicate new versions.)
     */
    @SerialName("sc_ver_code")
    var appVersionCode: String,
    /**
     * Project theme property.
     * Color for a ripple effect (on buttons, etc).
     */
    @SerialName("color_control_highlight")
    var colorControlHighLight: Double,
    /**
     * Project theme property.
     * Color for the app bar and other primary UI elements.
     */
    @SerialName("color_primary")
    var colorPrimary: Double,
    /**
     * Project theme property.
     * A secondary color for controls like checkboxes and text fields.
     */
    @SerialName("color_accent")
    var colorAccent: Double,
    /**
     * Project creation date.
     */
    @SerialName("my_sc_reg_dt")
    var projectCreationDate: String,
    /**
     * Project theme property.
     * Responsible for color in status bar.
     */
    @SerialName("color_primary_dark")
    var colorPrimaryDark: Double,
    /**
     * Project theme property.
     * Responsible for color while on widget focused.
     */
    @SerialName("color_control_normal")
    var colorControlNormal: Double,
    @SerialName("sc_ver_name")
    var appVersionName: String,
    /**
     * Project name (Not to be confused with [appName].)
     */
    @SerialName("my_ws_name")
    var projectName: String,
    /**
     * Is a custom icon installed.
     */
    @SerialName("custom_icon")
    var customIcon: Boolean
)
