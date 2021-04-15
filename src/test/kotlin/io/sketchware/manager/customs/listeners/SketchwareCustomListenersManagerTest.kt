package io.sketchware.manager.customs.listeners

import io.sketchware.models.customs.CustomEvent
import io.sketchware.models.customs.CustomListenerGroup
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class SketchwareCustomListenersManagerTest {

    private val manager = SketchwareCustomListenersManager(
        String(javaClass.getResourceAsStream("/customs/listener.json")!!.readBytes()),
        String(javaClass.getResourceAsStream("/customs/listener.json")!!.readBytes()),
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
        assertFalse(manager.listeners.contains(listener))
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