package io.sketchware.manager.collections.managers

import io.sketchware.models.collections.FileCollectionItem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

private const val fileCollectionValue =
    "{\"data\":\"test.png\",\"name\":\"test\"}\n{\"data\":\"test2.png\",\"name\":\"test2\"}"

internal class FileCollectionManagerTest {

    private val manager = FileCollectionManager(fileCollectionValue, File("/"))

    @Test
    fun getAll() {
        assertNotNull(manager.all)
    }

    @Test
    fun addItem() {
        manager.addItem(FileCollectionItem("test", ""))
        assertNotNull(manager.all.find { it.name == "test" })
    }

    @Test
    fun removeItem() {
        val item = FileCollectionItem("test1", "")
        manager.addItem(item)
        assertNotNull(manager.all.find { it.name == "test1" })
        manager.removeItem(item)
        assertNull(manager.all.find { it.name == "test1" })
    }

}