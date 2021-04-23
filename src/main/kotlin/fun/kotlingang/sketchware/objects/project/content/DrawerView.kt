package `fun`.kotlingang.sketchware.objects.project.content

class DrawerView(fileName: String) : CustomView(fileName) {
    /**
     * Converts [DrawerView] to [FileDataModel].
     */
    override fun toFileDataModel(): FileDataModel {
        return super.toFileDataModel().also { it.fileType = FileType.DRAWER }
    }
}