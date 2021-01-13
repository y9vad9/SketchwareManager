# Sketchware Manager

Coroutine-based library for managing sketchware projects, collections and etc.

## Projects 📂

The library supports Sketchware, Sketchware Pro projects ✔

### Example:

```kotlin
val manager = SketchwareProjects("../.sketchware")
manager.getProjects().forEach { project ->
    when(project) {
        is SketchwareProProject -> println("I am a sketchware pro project")
        is SketchwareProject -> println("I am a sketchware project")
    }
}
```

Also, an instance of the SketchwarePro class, for example, has its own functionality only for it:

```kotlin
val project = manager.getProjects()[0] as SketchwareProProject // only as example :)
println(project.getProguardRules()) // prints proguard rules in project
project.setProguardRules("..") // sets proguard rules
// and another funcs..
```
### Managing project logic, view, etc.
The library provides the ability to manage logic, layout, resources and other project information,
but this isn't stable implementation, and the public API can change at any time.
#### Example:
```kotlin
val logicManager = project.logicManager
val mainActivityEvents = logicManager.getEvents("MainActivity")
mainActivityEvents.forEach { event -> event.apply {
        println("event name: $name")
        println("event type: $type")
    }
}
```
More: [Data tutorials](https://github.com/y9neon/SketchwareManager/tree/master/tutorials/Receiving%20and%20managing%20project%20data%20(logic%2C%20view%2C%20resources%2C%20libraries)).

## Collections 📦

Our library provides the ability to get/modify a list of Collections, which is not available with conventional methods
due to the nature of their storage. Example:

```kotlin
val collectionManager = SketchwareCollections(File(__path__))
println(collectionManager.moreblocks.getCollection()) // prints all moreblocks
```

More: [Collections tutorial](https://github.com/y9neon/SketchwareManager/tree/master/tutorials/Collections).

## Implementation

#### build.gradle:

```groovy
repositories {
    maven { url 'https://dl.bintray.com/kotlingang/maven' }
}
dependencies {
    implementation 'io.sketchware:Sketchware-Manager:alpha-2.2.2'
}
```

#### build.gradle.kts:

```kotlin
repositories {
    maven("https://dl.bintray.com/kotlingang/maven")
}
dependencies {
    implementation("io.sketchware:Sketchware-Manager:alpha-2.2.2")
}
```
