# Projects

## What is covered?

This tutorial will show how to get projects and get information of it.

## Get projects

The library provides the ability to get a list of projects and determine the type of project as needed. It also has
common functionality for common needs. It has a Sketchware Projects class in which there is a suspend function
getProjects that returns a `List<Project>`, where Project can be either `SketchwareProject` or `SketchwareProProject`.
Let's check it out:

#### In JVM application it will look like:

```kotlin
suspend fun main() {
    val projects = SketchwareProjects(sketchware_folder).getProjects()
    // something after it..
}
```

#### In Android application it will look like:

```kotlin
override fun onCreate(b: Bundle) = scope.launch {
    val projects = SketchwareProjects(sketchware_folder).getProjects()
    // something after it..
}
```

Every project in Sketchware has basic information about what version the project was created on, what the project's
batch name, and so on...

Let's try to get a package of all projects:

```kotlin
projects.forEach { project ->
    val projectPackageName = project.information.myScPkgName
    println(projectPackageName) // it print packages for each project
}
```

It also has a lot of other information about project that you can
check [here](https://github.com/y9neon/Sketchware-Project-Manager/blob/dev/src/main/kotlin/io/sketchware/project/models/ProjectConfig.kt)
.
