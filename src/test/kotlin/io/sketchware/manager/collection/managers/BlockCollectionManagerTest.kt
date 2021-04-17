package io.sketchware.manager.collection.managers

import io.sketchware.model.collection.BlockCollectionItem
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.io.File

private const val blockCollectionValue =
    "{\"data\":\"{\\\"color\\\":-13851166,\\\"id\\\":\\\"99000012\\\",\\\"nextBlock\\\":-1,\\\"opCode\\\":\\\"finishActivity\\\",\\\"parameters\\\":[],\\\"spec\\\":\\\"Finish Activity\\\",\\\"subStack1\\\":-1,\\\"subStack2\\\":-1,\\\"type\\\":\\\"f\\\",\\\"typeName\\\":\\\"\\\"}\\n\",\"name\":\"vv\"}\n"

internal class BlockCollectionManagerTest {
    private val manager = BlockCollectionManager(blockCollectionValue, File("/"))

    @Test
    fun getAll() {
        assertNotNull(manager.all)
    }

    @Test
    fun addItem() {
        manager.addItem(BlockCollectionItem("test", listOf()))
        assertNotNull(manager.all.find { it.name == "test" })
    }

    @Test
    fun removeItem() {
        val block = BlockCollectionItem("test1", listOf())
        manager.addItem(block)
        assertNotNull(manager.all.find { it.name == "test1" })
        manager.removeItem(block)
        assertNull(manager.all.find { it.name == "test1" })
    }

}