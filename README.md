![image](https://user-images.githubusercontent.com/32961194/112753044-b9f0a680-8fde-11eb-9af2-a57b10369477.png)
A coroutine-based library for Sketchware Management. Provides the ability to work with projects(its logic, views, and so
on.), collections and custom elements of Sketchware mods as custom components. Mods such as Pro and Studio (CodeGo) are
now supported.

Why this particular library?

- **Fast**: Due to the most optimized work with Sketchware (and mods) and Kotlin's coroutines, the library works as
  quickly as possible.
- **Easy to use**: Due to coroutines and convenient Kotlin syntax, working with the library is as pleasant as possible.
- **Modern**: SketchwareManager is Kotlin-first and uses modern libraries including Coroutines, Kotlin Serialization.

> This version still in developing. Not all are ready yet (currently paused).

### The design I want to achieve:
Project Management:
```kotlin
val manager = SketchwareManager().getProjects().forEach { project -> // suspend point on getProjects()
    // project contains only info from config in mysc/list/%d/project.
    println(project.projectName) // prints project name
    // let's try to edit something:
    val editor = project.editor() // suspend point
    editor.activities.first().logic.apply {
        variables.first().name = "new name"
        save() // suspend point
    }
}
```
Also, there will be type-safe builders for views and blocks:
```kotlin
val blocks = project.editor().logic.events[0].blocks
for(block in blocks) {
    when(block) {
        is IfStyledBlock -> foo()
        is BooleanPrimitiveBlock -> bar()
        // ...
    }
}
```


Collections Management:
```kotlin
val manager = CollectionManager()
println(manager.getMoreblocks().map { moreblock -> moreblock.name })
```
