package io.sketchware.manager.project.util

import io.sketchware.model.project.ProjectResource
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.File

private const val resourcesValue = "@images\n" +
        "{\"resFullName\":\"png.png\",\"resName\":\"png\",\"resType\":1}\n" +
        "{\"resFullName\":\"cuadro1.png\",\"resName\":\"cuadro1\",\"resType\":1}" +
        "@sounds\n" +
        "{\"resFullName\":\"sound.mp3\",\"resName\":\"sound\",\"resType\":1}" +
        "@fonts\n" +
        "{\"resFullName\":\"texto1.ttf\",\"resName\":\"texto1\",\"resType\":1}\n" +
        "{\"resFullName\":\"texto2.ttf\",\"resName\":\"texto2\",\"resType\":1}"

internal class ResourcesManagerTest {

    private val resourcesManager = ResourcesManager(resourcesValue, File(""))

    @Test
    fun getImages() {
        assertNotNull(resourcesManager.images)
    }

    @Test
    fun getFonts() {
        assertNotNull(resourcesManager.fonts)
    }

    @Test
    fun getSounds() {
        assertNotNull(resourcesManager.sounds)
    }

    @Test
    fun addImage() {
        resourcesManager.addImage(ProjectResource("image.png", "image", 1))
        assertNotNull(resourcesManager.images.find { it.fullName == "image.png" })
    }

    @Test
    fun addFont() {
        resourcesManager.addFont(ProjectResource("font.ttf", "font", 1))
        assertNotNull(resourcesManager.fonts.find { it.fullName == "font.ttf" })
    }

    @Test
    fun addSound() {
        resourcesManager.addSound(ProjectResource("sound1.mp3", "sound1", 1))
        assertNotNull(resourcesManager.sounds.find { it.fullName == "sound1.mp3" })
    }

    @Test
    fun removeImage() {
        resourcesManager.removeImage("cuadro1")
        assertNull(resourcesManager.images.find { it.name == "cuadro1" })
    }

    @Test
    fun removeFont() {
        resourcesManager.removeFont("texto1")
        assertNull(resourcesManager.fonts.find { it.name == "texto1" })
    }

    @Test
    fun removeSound() {
        resourcesManager.removeSound("sound.mp3")
        assertNull(resourcesManager.sounds.find { it.name == "sound" })
    }

}