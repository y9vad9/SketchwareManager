package io.sketchware.project.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectConfig(
    @SerialName("sketchware_ver")
    var sketchwareVer: Double,
    @SerialName("sc_id")
    var scId: String,
    @SerialName("my_sc_pkg_name")
    var myScPkgName: String,
    @SerialName("my_app_name")
    var myAppName: String,
    @SerialName("sc_ver_code")
    var scVerCode: String,
    @SerialName("color_control_highlight")
    var colorControlHighLight: Double,
    @SerialName("color_primary")
    var colorPrimary: Double,
    @SerialName("color_accent")
    var colorAccent: Double,
    @SerialName("my_sc_reg_dt")
    var myScRegDt: String,
    @SerialName("color_primary_dark")
    var colorPrimaryDark: Double,
    @SerialName("color_control_normal")
    var colorControlNormal: Double,
    @SerialName("sc_ver_name")
    var scVerName: String,
    @SerialName("my_ws_name")
    var myWsName: String,
    @SerialName("custom_icon")
    var customIcon: Boolean
)