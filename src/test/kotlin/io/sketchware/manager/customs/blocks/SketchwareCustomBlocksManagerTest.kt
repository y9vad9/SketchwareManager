package io.sketchware.manager.customs.blocks

import io.sketchware.models.customs.CustomBlock
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class SketchwareCustomBlocksManagerTest {

    private val manager = SketchwareCustomBlocksManager(
        String(javaClass.getResourceAsStream("/customs/blocks/block.json")!!.readBytes()),
        String(javaClass.getResourceAsStream("/customs/blocks/palette.json")!!.readBytes()),
        File(""),
        File("")
    )

    @Test
    fun getBlocks() {
        assertNotNull(manager.blocks)
    }

    @Test
    fun getFreeId() {
        assertNull(manager.blocks.find { it.groupId == manager.freeId })
    }

    @Test
    fun addBlocksGroup() {
        val id = manager.freeId
        val group = manager.blocks[0].copy(groupId = id)
        manager.addBlocksGroup(group)
        assertNotNull(manager.blocks.find { it.groupId == id })
    }

    @Test
    fun removeBlocksGroup() {
        val group = manager.blocks[0]
        manager.removeBlocksGroup(group.groupId)
        assertFalse(manager.blocks.contains(group))
    }

    @Test
    fun removeBlock() {
        val group = manager.blocks[0]
        manager.removeBlock(group.groupId, group.blocks[0].name)
        assertFalse(manager.blocks[0].blocks.contains(group.blocks[0]))
    }

    @Test
    fun editBlocksGroup() {
        val group = manager.blocks[0]
        manager.editBlocksGroup(group.groupId) {
            it.hexColor = "hex"
        }
        assertNotNull(manager.blocks.find { it.hexColor == "hex" })
    }

    @Test
    fun addBlockToGroup() {
        val group = manager.blocks[0]
        manager.addBlockToGroup(
            group.groupId,
            CustomBlock("", "hex", "test", 0, listOf(), "", "")
        )
        assertNotNull(manager.blocks.find { it.name == "test" })
    }
}