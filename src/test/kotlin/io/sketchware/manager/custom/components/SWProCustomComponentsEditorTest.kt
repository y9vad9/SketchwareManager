package io.sketchware.manager.custom.components

import `fun`.kotlingang.sketchware.editors.customs.SWProCustomComponentsEditor
import `fun`.kotlingang.sketchware.objects.customs.CustomComponent
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.File

internal class SWProCustomComponentsEditorTest {

    private val manager = SWProCustomComponentsEditor(
        String(javaClass.getResourceAsStream("/customs/component.json")!!.readBytes()), File("")
    )

    @Test
    fun getComponents() {
        assertNotNull(manager.components)
    }

    @Test
    fun addComponent() {
        manager.addComponent(
            CustomComponent(
                0, "class", "desc", "", "", 380, "",
                "", "", "", "", ""
            )
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