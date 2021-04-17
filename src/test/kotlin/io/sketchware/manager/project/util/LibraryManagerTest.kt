package io.sketchware.manager.project.util

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import java.io.File

private const val libraryValue = "@firebaseDB\n" +
        "{\"adUnits\":[],\"data\":\"\",\"libType\":0,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}\n" +
        "@compat\n" +
        "{\"adUnits\":[],\"data\":\"\",\"libType\":1,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}\n" +
        "@admob\n" +
        "{\"adUnits\":[{\"id\":\"ca-app-pub-3538535077259745/1896727371\",\"name\":\"Banner\"},{\"id\":\"ca-app-pub-3538535077259745/2435783424\",\"name\":\"Intersticial\"}],\"data\":\"\",\"libType\":2,\"reserved1\":\"Banner : ca-app-pub-3538535077259745/1896727371\",\"reserved2\":\"Intersticial : ca-app-pub-3538535077259745/2435783424\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}\n" +
        "@googleMap\n" +
        "{\"adUnits\":[],\"data\":\"\",\"libType\":3,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}"

internal class LibraryManagerTest {

    private val manager = LibraryManager(libraryValue, File("/"))

    @Test
    fun getLibraries() {
        println(manager.libraries)
        assertNotNull(manager.libraries)
        assertFalse { manager.libraries.isEmpty() }
    }

    @Test
    fun editLibraryData() {
        manager.editLibraryData("admob") {
            this.data = "test"
        }
        assertNotNull(manager.libraries.find { it.information.data == "test" })
    }
}