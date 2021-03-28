![image](https://user-images.githubusercontent.com/32961194/112753044-b9f0a680-8fde-11eb-9af2-a57b10369477.png)
A coroutine-based library for Sketchware Management. Provides the ability to work with projects(its logic, views, and so on.), collections and custom elements of Sketchware mods as custom components. Mods such as Pro and Studio are now supported.

Why this particular library?
- **Fast**: Due to the most optimized work with Sketchware (and mods) and Kotlin's coroutines, the library works as quickly as possible.
- **Easy to use**: Due to coroutines and convenient Kotlin syntax, working with the library is as pleasant as possible.
- **Modern**: SketchwareManager is Kotlin-first and uses modern libraries including Coroutines, Kotlin Serialization.

## Implementation
```kotlin
repositories {
    maven("https://dl.kotlingang.fun")
}
dependencies {
    implementation("io.sketchware:Sketchware-Manager:dev-2.4.0")
}
```
## Quick Start
To get a list of projects just make next:
```kotlin
// let's initialize base manager for sketchware:
val manager = SketchwareManager(sketchwareFolder)
// let's get projects manager from base manager for sketchware 
// and get projects:
val projects = manager.projectsManager.getProjects()
// let's print all:
projects.forEach { project: SketchwareProject ->
    // get config or current project
    val projectConfig = project.getConfig()
    // print id of project what was found.
    println("Project with id ${projectConfig.projectId} found.")
}
```
Also, we can get some sketchware collections, for example, moreblocks:
```kotlin
// let's initialize base manager for sketchware:
val manager = SketchwareManager(sketchwareFolder)
// get moreblocks as List:
val moreblocks = manager.collectionsManager.getMoreblocksManager().all
// let's print moreblocks data:
moreblocks.forEach(::println)
```
Full documentation you can get [here](https://swmanager.kotlingang.fun).
## R8 / Proguard
If you use Proguard, you may need to add rules for [Coroutines](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-core/jvm/resources/META-INF/proguard/coroutines.pro).