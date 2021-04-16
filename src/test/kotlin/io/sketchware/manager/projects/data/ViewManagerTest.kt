package io.sketchware.manager.projects.data

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.File

internal class ViewManagerTest {

    private val manager = ViewManager(
        String(javaClass.getResourceAsStream("/project/data/view")!!.readBytes()), File("")
    )

    @Test
    fun getView() {
        assertNotNull(manager.getView("_drawer_main"))
        assertNotNull(manager.getView("main"))
        assertNotNull(manager.getView("main", "fab"))
    }

    @Test
    fun editView() {
        manager.editView("main") {
            it.forEach { widget -> widget.id = "test" }
        }
        assertTrue { manager.getView("main")?.all { it.id == "test" } == true }
    }
}