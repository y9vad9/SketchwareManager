package io.sketchware.manager.custom.listeners

import `fun`.kotlingang.sketchware.editors.customs.SWProCustomListenersEditor
import `fun`.kotlingang.sketchware.objects.customs.CustomEvent
import `fun`.kotlingang.sketchware.objects.customs.CustomListenerGroup
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.File

internal class SWProCustomListenersEditorTest {

    private val manager = SWProCustomListenersEditor(
        String(javaClass.getResourceAsStream("/customs/events.json")!!.readBytes()),
        String(javaClass.getResourceAsStream("/customs/listeners.json")!!.readBytes()),
        File(""),
        File("")
    )

    @Test
    fun getListeners() {
        assertNotNull(manager.listeners)
    }

    @Test
    fun addListenerGroup() {
        manager.addListenerGroup(
            CustomListenerGroup(
                "test", false, "", "",
                listOf(
                    CustomEvent(
                        listOf(), 0, "some_id", "dff", "flkfwwf", "fwkwffw", "wffwfw"
                    )
                )
            )
        )
        assertNotNull(manager.listeners.find { it.name == "test" })
    }

    @Test
    fun removeListenerGroup() {
        val listener = manager.listeners[0]
        manager.removeListenerGroup(listener.name)
        assertNull(manager.listeners.find { it.name == listener.name })
    }

    @Test
    fun editListenerGroup() {
        val listener = manager.listeners[0]
        manager.editListenerGroup(listener.name) {
            it.name = "damn"
        }
        assertNotNull(manager.listeners.find { it.name == "damn" })
    }

}