package io.sketchware.manager.customs.components

import io.sketchware.models.customs.CustomComponent
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class SketchwareCustomComponentsManagerTest {

    private val manager = SketchwareCustomComponentsManager(
        String(javaClass.getResourceAsStream("/customs/component.json")!!.readBytes()), File("")
    )

    @Test
    fun getComponents() {
        assertNotNull(manager.components)
    }

    @Test
    fun addComponent() {
        manager.addComponent(CustomComponent(
            0, "class", "desc", "", "", 380, "",
            "", "", "", "", "")
        )
        assertNotNull(manager.components.find { it.id == 380 })
    }

    @Test
    fun removeComponent() {
        val component = manager.components[0]
        manager.removeComponent(component)
        assertFalse(manager.components.contains(component))
    }
}