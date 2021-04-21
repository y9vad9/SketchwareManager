package io.sketchware.manager.projects.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ViewManagerTest {

    private val manager = ViewManager(
        String(javaClass.getResourceAsStream("/project/data/view")!!.readBytes()), File("")
    )

    @Test
    fun getView() {
        val view = manager.getView("main")
        assertNotNull(view)
    }

    @Test
    fun removeView() {
        val view = manager.getView("main")!!
        assertTrue(view.remove())
        assertNull(manager.getView("main"))
    }

    @Test
    fun getRoot() {
        val view = manager.getView("main")
        assertNotNull(view?.root)
        assertTrue { view?.root?.children?.isNotEmpty() == true }
    }

}