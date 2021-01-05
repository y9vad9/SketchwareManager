import io.sketchware.SketchwareProjects
import io.sketchware.encryptor.FileEncryptor
import io.sketchware.project.SketchwareProProject
import io.sketchware.project.SketchwareProject
import io.sketchware.project.models.ProjectDestination
import io.sketchware.project.models.sketchwarepro.ProguardData
import io.sketchware.toJson
import java.io.File
import java.util.*
import kotlin.random.asKotlinRandom

suspend fun main() {
    val path = "D:\\.sketchware"
    val manager = SketchwareProjects(path)
    manager.getProjects()?.get(0)?.apply {
        edit {
            scId = "9999"
            myScPkgName = "pkg.new"
            scVerCode = "999test"
        }
    }
    print(manager.getProjects()?.get(0)?.information.toJson())
}