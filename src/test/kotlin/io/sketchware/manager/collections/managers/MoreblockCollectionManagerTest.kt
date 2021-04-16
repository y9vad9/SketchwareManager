package io.sketchware.manager.collections.managers

import io.sketchware.models.collections.MoreblockCollectionItem
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.File

internal class MoreblockCollectionManagerTest {
    private val manager = MoreblockCollectionManager(String(
        javaClass.getResourceAsStream("/collections/moreblocks")!!.readBytes()), File("")
    )

    @Test
    fun getAll() {
        assertNotNull(manager.all)
    }

    @Test
    fun addItem() {
        manager.addItem(MoreblockCollectionItem("test", listOf(), listOf()))
        assertNotNull(manager.all.find { it.name == "test" })
    }

    @Test
    fun removeItem() {
        val moreblock = MoreblockCollectionItem("test1", listOf(), listOf())
        manager.addItem(moreblock)
        assertNotNull(manager.all.find { it.name == "test1" })
        manager.all.find { it.name == "test1" }?.let { manager.removeItem(it) }
        assertNull(manager.all.find { it.name == "test1" })
    }

}