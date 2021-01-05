package io.sketchware.project.models

import java.io.File

abstract class ProjectData

data class SketchwareProjectData(
    var folder: File,
    /* File with data about activities, custom views */
    var file: File,
    /* File with data about libraries */
    var library: File?,
    /* File with data about logic (moreblocks, events and etc.) */
    var logic: File?,
    /* File with data about all resources in project */
    var resource: File?,
    /* File with data about views */
    var view: File?
): ProjectData()

data class SketchwareProProjectData(
    var folder: File,
    /* File with data about activities, custom views */
    var file: File,
    /* File with data about libraries */
    var library: File?,
    /* File with data about logic (moreblocks, events and etc.) */
    var logic: File?,
    /* File with data about all resources in project */
    var resource: File?,
    /* File with data about views */
    var view: File?,
    /* File with data about proguard status (is enabled, is building in debug mode) */
    var proguard: File,
    /* File with proguard rules */
    var proguardRules: File,
    /* i don't understand too what is it 0_o, but may be it something important */
    var stringFog: File,
    /* it empty in my test project.. for what is it? */
    var projectConfig: File
): ProjectData()