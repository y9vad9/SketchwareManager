package io.sketchware.models.sketchware

import java.io.File

abstract class ProjectResources

open class SketchwareProjectResources(
    var images: File,
    var icons: File,
    var fonts: File,
    var sounds: File
) : ProjectResources()