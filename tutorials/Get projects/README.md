# Projects

## What is covered?

This tutorial will show how to get projects and get information of it.

## Get projects

The library provides the ability to get a list of projects and determine the type of project as needed. It also has
common functionality for common needs. It has a Sketchware Projects class in which there is a suspend function
getProjects that returns a `List<SketchwareProject>`, where SketchwareProject can be `SketchwareProProject` (you do not have to check if SketchwarePro's project is a project, as there is a common logic for normal and modified projects).
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
    val projectPackageName = project.getConfig().packageName
    println(projectPackageName) // it print packages for each project
}
```
