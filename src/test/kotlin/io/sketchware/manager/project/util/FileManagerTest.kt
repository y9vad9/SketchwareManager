package io.sketchware.manager.project.util

import io.sketchware.annotation.ExperimentalSWManagerAPI
import io.sketchware.model.project.*
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

internal class FileManagerTest {

    private val fileManager = FileManager(fileValue, File("/"))

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
                "file_name_test", FileType.Activity, KeyboardSetting.Unspecified, 0,
                Orientation.Both, ActivityTheme.NO_ACTIONBAR
            )
        )
        assertNotNull(fileManager.activities.find { it.activityName == "FileNameTestActivity" })
    }

    @Test
    fun addCustomView() {
        fileManager.addCustomView(
            FileDataModel(
                "file_name_test2", FileType.CustomView, KeyboardSetting.Unspecified, 0,
                Orientation.Both, ActivityTheme.NO_ACTIONBAR
            )
        )
        assertNotNull(fileManager.customViews.find { it.fileName == "file_name_test2" })
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun removeActivity() {
        fileManager.removeActivity("file_name_test")
        assertNull(fileManager.activities.find { it.activityName == "FileNameTestActivity" })
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun removeCustomView() {
        fileManager.removeCustomView("file_name_test2")
        assertNull(fileManager.customViews.find { it.fileName == "file_name_test2" })
    }
}