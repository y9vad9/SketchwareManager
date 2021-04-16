package io.sketchware.manager.projects.data

import io.sketchware.annotations.ExperimentalSWManagerAPI
import io.sketchware.models.projects.BlockModel
import io.sketchware.models.projects.ComponentModel
import io.sketchware.models.projects.SketchwareEventModel
import io.sketchware.models.projects.VariableModel
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

private const val logicValue = "@MainActivity.java_var\n" +
        "2:varstr\n" +
        "3:varmal\n" +
        "\n" +
        "@MainActivity.java_list\n" +
        "3:listmal\n" +
        "\n" +
        "@MainActivity.java_func\n" +
        "Snackbar:Snackbar on %m.view.view with label %s.label\n" +
        "\n" +
        "@MainActivity.java_components\n" +
        "{\"componentId\":\"intent\",\"param1\":\"\",\"param2\":\"\",\"param3\":\"\",\"type\":1}\n" +
        "{\"componentId\":\"prefs\",\"param1\":\"prefs\",\"param2\":\"\",\"param3\":\"\",\"type\":2}\n" +
        "{\"componentId\":\"player\",\"param1\":\"\",\"param2\":\"\",\"param3\":\"\",\"type\":8}\n" +
        "{\"componentId\":\"picker\",\"param1\":\"*/*\",\"param2\":\"\",\"param3\":\"\",\"type\":16}\n" +
        "\n" +
        "@MainActivity.java_events\n" +
        "{\"eventName\":\"onBackPressed\",\"eventType\":3,\"targetId\":\"onBackPressed\",\"targetType\":0}\n" +
        "{\"eventName\":\"onFilesPicked\",\"eventType\":2,\"targetId\":\"picker\",\"targetType\":16}\n" +
        "{\"eventName\":\"onFilesPickedCancel\",\"eventType\":2,\"targetId\":\"picker\",\"targetType\":16}\n" +
        "{\"eventName\":\"onClick\",\"eventType\":1,\"targetId\":\"edittext1\",\"targetType\":5}\n" +
        "{\"eventName\":\"onClick\",\"eventType\":1,\"targetId\":\"imageview1\",\"targetType\":6}\n" +
        "{\"eventName\":\"onClick\",\"eventType\":4,\"targetId\":\"button\",\"targetType\":3}\n" +
        "\n" +
        "@MainActivity.java_onBackPressed_onBackPressed\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":11,\"opCode\":\"doToast\",\"parameters\":[\"1\"],\"spec\":\"Toast %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "{\"color\":-13851166,\"id\":\"11\",\"nextBlock\":-1,\"opCode\":\"doToast\",\"parameters\":[\"2\"],\"spec\":\"Toast %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_picker_onFilesPicked\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":11,\"opCode\":\"doToast\",\"parameters\":[\"\"],\"spec\":\"Toast %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "{\"color\":-13851166,\"id\":\"11\",\"nextBlock\":-1,\"opCode\":\"finishActivity\",\"parameters\":[],\"spec\":\"Finish Activity\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\"f\",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_Snackbar_moreBlock\n" +
        "{\"color\":-10701022,\"id\":\"10\",\"nextBlock\":-1,\"opCode\":\"addSourceDirectly\",\"parameters\":[\"android.support.design.widget.Snackbar.make(_view, _label.toString(), android.support.design.widget.Snackbar.LENGTH_SHORT).show();\"],\"spec\":\"add source directly %s.inputOnly\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_imageview1_onClick\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":-1,\"opCode\":\"finishActivity\",\"parameters\":[],\"spec\":\"Finish Activity\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\"f\",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_edittext1_onClick\n" +
        "{\"color\":-7711273,\"id\":\"10\",\"nextBlock\":-1,\"opCode\":\"definedFunc\",\"parameters\":[\"checkbox1\",\"label\"],\"spec\":\"Snackbar on %m.view.view with label %s.label\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_button_onClick\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":-1,\"opCode\":\"finishActivity\",\"parameters\":[],\"spec\":\"Finish Activity\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\"f\",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_onCreate_initializeLogic\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":11,\"opCode\":\"copyToClipboard\",\"parameters\":[\"1\"],\"spec\":\"copyToClipboard %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "{\"color\":-13851166,\"id\":\"11\",\"nextBlock\":-1,\"opCode\":\"setTitle\",\"parameters\":[\"test\"],\"spec\":\"Activity set title %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "\n" +
        "@MainActivity.java_picker_onFilesPickedCancel\n" +
        "{\"color\":-13851166,\"id\":\"10\",\"nextBlock\":11,\"opCode\":\"doToast\",\"parameters\":[\"1\"],\"spec\":\"Toast %s\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}\n" +
        "{\"color\":-13851166,\"id\":\"11\",\"nextBlock\":-1,\"opCode\":\"intentSetAction\",\"parameters\":[\"intent\",\"ACTION_DIAL\"],\"spec\":\"%m.intent setAction %m.intentAction\",\"subStack1\":-1,\"subStack2\":-1,\"type\":\" \",\"typeName\":\"\"}"

internal class LogicManagerTest {

    private var manager = LogicManager(logicValue, File(""))

    private val testActivity = "MainActivity"

    @BeforeEach
    fun initManager() {
        manager = LogicManager(logicValue, File(""))
    }

    @Test
    fun getEvents() {
        assertNotNull(manager.getEvents(testActivity))
    }

    @Test
    fun getEvent() {
        val event = manager.getEvents(testActivity)!![0]
        assertNotNull(manager.getEvent(testActivity, event.targetId, event.name))
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun editEventInfo() {
        val event = manager.getEvents(testActivity)!![0]
        manager.editEventInfo(testActivity, event.targetId, event.name) {
            name = "testName"
        }
        assertNotNull(manager.getEvents(testActivity)!!.find { it.name == "testName" })
    }

    @Test
    fun removeEvent() {
        val event = manager.getEvents(testActivity)!![0]
        manager.removeEvent(testActivity, event.targetId, event.name)
        assertNull(manager.getEvent(testActivity, event.targetId, event.name))
    }

    @Test
    fun addEvent() {
        manager.addEvent(
            testActivity, SketchwareEventModel(
                "testName", 0, "targetTest", 0
            ), listOf()
        )
        assertNotNull(manager.getEvent(testActivity, "targetTest", "testName"))
    }

    @Test
    fun getVariables() {
        assertNotNull(manager.getVariables(testActivity))
    }

    @Test
    fun getVariable() {
        assertNotNull(manager.getVariable(testActivity, manager.getVariables(testActivity)!![0].name))
    }

    @Test
    fun removeVariable() {
        val variable = manager.getVariables(testActivity)!![0]
        manager.removeVariable(testActivity, variable.name)
        assertNull(manager.getVariable(testActivity, variable.name))
    }

    @Test
    fun addVariable() {
        val variable = VariableModel(0, "test")
        manager.addVariable(testActivity, variable.name, variable.type)
        assertNotNull(manager.getVariable(testActivity, variable.name))
    }

    @Test
    fun getComponents() {
        assertNotNull(manager.getComponents(testActivity))
    }

    @Test
    fun getComponent() {
        assertNotNull(manager.getComponent(testActivity, manager.getComponents(testActivity)!![0].id))
    }

    @Test
    fun removeComponent() {
        val component = manager.getComponents(testActivity)!![0]
        manager.removeComponent(testActivity, component.id)
        assertNull(manager.getComponent(testActivity, component.id))
    }

    @Test
    fun addComponent() {
        val component = ComponentModel("test_id", "param", "", "", 0)
        manager.addComponent(testActivity, component)
        assertNotNull(manager.getComponent(testActivity, component.id))
    }

    @Test
    fun getMoreblocks() {
        assertNotNull(manager.getMoreblocks(testActivity))
    }

    @Test
    fun getMoreblock() {
        val moreblock = manager.getMoreblocks(testActivity)!![0]
        assertNotNull(manager.getMoreblock(testActivity, moreblock.name))
    }

    @Test
    fun getMoreblockLogic() {
        val moreblock = manager.getMoreblocks(testActivity)!![0]
        assertNotNull(manager.getMoreblockLogic(testActivity, moreblock.name))
    }

    @Test
    fun editMoreblockLogic() {
        val moreblock = manager.getMoreblocks(testActivity)!![0]
        var list: List<BlockModel> = listOf()
        manager.editMoreblockLogic(testActivity, moreblock.name) {
            add(
                BlockModel(
                    -131414, 0, -1, "opcode",
                    listOf(), listOf(), -1, -1, "type", "typeName"
                )
            )
        }
        assertTrue { manager.getMoreblockLogic(testActivity, moreblock.name)?.find { it.color == -131414 } != null }
    }

    @OptIn(ExperimentalSWManagerAPI::class)
    @Test
    fun editMoreblockInfo() {
        val moreblock = manager.getMoreblocks(testActivity)!![0]
        manager.editMoreblockInfo(testActivity, moreblock.name) {
            it.name = "test"
        }
        assertNotNull(manager.getMoreblocks(testActivity)!!.find { it.name == "test" })
    }

    @Test
    fun removeMoreblock() {
        val moreblock = manager.getMoreblocks(testActivity)!![0]
        manager.removeMoreblock(testActivity, moreblock.name)
        assertNull(manager.getMoreblock(testActivity, moreblock.name))
    }

    @Test
    fun getEventLogic() {
        val event = manager.getEvents(testActivity)!![0]
        assertNotNull(manager.getEvent(testActivity, event.targetId, event.name))
    }

    @Test
    fun editEventLogic() {
        val event = manager.getEvents(testActivity)!![0]
        manager.editEventLogic(testActivity, event.targetId, event.name) {
            it.forEach { block ->
                block.color = 0
            }
        }
        assertTrue { manager.getEventLogic(testActivity, event.targetId, event.name)!!.all { it.color == 0 } }
    }

    @Test
    fun getOnCreateLogic() {
        assertNotNull(manager.getOnCreateLogic(testActivity))
    }

    @Test
    fun editOnCreateLogic() {
        manager.editOnCreateLogic(testActivity) {
            it.forEach { block ->
                block.color = 0
            }
        }
        assertTrue { manager.getOnCreateLogic(testActivity)!!.all { it.color == 0 } }
    }
}