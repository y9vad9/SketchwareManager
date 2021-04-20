package io.sketchware.manager.projects.util

import io.sketchware.model.project.widget.TextViewWidget
import io.sketchware.model.project.widget.ViewGroupWidget
import org.junit.jupiter.api.Test
import java.io.File

internal class ViewManagerTest {

    private val manager = ViewManager(
        String(javaClass.getResourceAsStream("/project/data/view")!!.readBytes()), File("")
    )

    @Test
    fun getView() {
        val view = manager.getView("main")!!
        view.root.children.forEach {
            if (it is ViewGroupWidget)
                println(it.children)
            else if (it is TextViewWidget)
                it.text = "some text"

            it.id = "some_new_id"
        }
    }

    @Test
    fun editView() {

    }
}