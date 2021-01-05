import io.sketchware.SketchwareProjects
import io.sketchware.project.models.ProjectDestination
import io.sketchware.toJson
import java.util.*

private val scanner = Scanner(System.`in`)

suspend fun main() {
    val manager = SketchwareProjects("D:\\.sketchware")
    val projects = manager.getProjects()!!
    println(projects.toString())
    val project = projects[0]
    println(project.information.toJson())
    println("clone/delete")
    when(scanner.nextLine()) {
        "clone" -> {
            println("cloing")
            val freeId = manager.nextFreeId
            project.clone(freeId, ProjectDestination.from("D:\\.sketchware", freeId))
        }
        "delete" -> project.delete()
    }
    println("..")
    main()
}