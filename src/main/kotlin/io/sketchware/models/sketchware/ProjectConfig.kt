package io.sketchware.models.sketchware

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectConfig(
    @SerialName("sketchware_ver")
    var sketchwareVersion: Double,
    @SerialName("sc_id")
    var projectId: String,
    @SerialName("my_sc_pkg_name")
    var packageName: String,
    @SerialName("my_app_name")
    var appName: String,
    @SerialName("sc_ver_code")
    var appVersionCode: String,
    @SerialName("color_control_highlight")
    var colorControlHighLight: Double,
    @SerialName("color_primary")
    var colorPrimary: Double,
    @SerialName("color_accent")
    var colorAccent: Double,
    @SerialName("my_sc_reg_dt")
    var projectCreationDate: String,
    @SerialName("color_primary_dark")
    var colorPrimaryDark: Double,
    @SerialName("color_control_normal")
    var colorControlNormal: Double,
    @SerialName("sc_ver_name")
    var appVersionName: String,
    @SerialName("my_ws_name")
    var projectName: String,
    @SerialName("custom_icon")
    var customIcon: Boolean
)