package io.sketchware.manager.projects.editor

import `fun`.kotlingang.sketchware.annotations.ExperimentalSWManagerAPI
import `fun`.kotlingang.sketchware.editors.project.ContentEditor
import `fun`.kotlingang.sketchware.objects.project.content.*
import io.sketchware.model.project.content.KeyboardSetting
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

private const val fileValue = "@activity\n" +
    "{\"fileName\":\"main\",\"fileType\":0,\"keyboardSetting\":2,\"options\":2,\"orientation\":2,\"theme\":-1}\n" +
    "{\"fileName\":\"menu\",\"fileType\":0,\"keyboardSetting\":2,\"options\":2,\"orientation\":2,\"theme\":-1}\n" +
    "{\"fileName\":\"editor\",\"fileType\":0,\"keyboardSetting\":2,\"options\":2,\"orientation\":2,\"theme\":-1}\n" +
    "{\"fileName\":\"collage\",\"fileType\":0,\"keyboardSetting\":2,\"options\":2,\"orientation\":2,\"theme\":-1}\n" +
    "@customview\n" +
    "{\"fileName\":\"files\",\"fileType\":1,\"keyboardSetting\":0,\"options\":0,\"orientation\":2,\"theme\":-1}\n" +
    "{\"fileName\":\"custom\",\"fileType\":1,\"keyboardSetting\":0,\"options\":0,\"orientation\":2,\"theme\":-1}\n" +
    "{\"fileName\":\"_drawer_editor\",\"fileType\":2,\"keyboardSetting\":0,\"options\":0,\"orientation\":2,\"theme\":-1}"

internal class ContentEditorTest {

    private val fileManager = ContentEditor(fileValue, File("/"))

    @Test
    fun getActivities() {
        assertNotNull(fileManager.activities)
        assertFalse { fileManager.activities.isEmpty() }
    }

    @Test
    fun getCustomViews() {
        assertNotNull(fileManager.customViews)
        assertFalse { fileManager.activities.isEmpty() }
    }

    @Test
    fun addActivity() {
        fileManager.addActivity(
            FileDataModel(
                "file_name_test", FileType.ACTIVITY, KeyboardSetting.UNSPECIFIED, listOf(ActivityOption.DRAWER),
                Orientation.BOTH, ActivityTheme.NO_ACTIONBAR
            )
        )
        assertNotNull(fileManager.activities.find { it.javaName == "FileNameTestActivity" })
    }

    @Test
    fun addCustomView() {
        fileManager.addCustomView(
            FileDataModel(
                "file_name_test2", FileType.CUSTOMVIEW, KeyboardSetting.UNSPECIFIED, listOf(),
                Orientation.BOTH, ActivityTheme.NO_ACTIONBAR
            )
        )
        assertNotNull(fileManager.customViews.find { it.fileName == "file_name_test2" })
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun removeActivity() {
        fileManager.removeActivity("file_name_test")
        assertNull(fileManager.activities.find { it.javaName == "FileNameTestActivity" })
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun removeCustomView() {
        fileManager.removeCustomView("file_name_test2")
        assertNull(fileManager.customViews.find { it.fileName == "file_name_test2" })
    }
}