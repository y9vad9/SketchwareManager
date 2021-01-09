# Sketchware Manager

Coroutine-based library for managing sketchware projects, collections and etc.

## Projects 📂

The library supports Sketchware, Sketchware Pro projects ✔

### Example:

```kotlin
val manager = SketchwareProjects("../.sketchware")
manager.getProjects().forEach { project ->
    when(project) {
        is SketchwareProject -> println("I am a sketchware project")
        is SketchwareProProject -> println("I am a sketchware pro project")
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

## Collections 📦

Our library provides the ability to get/modify a list of Collections, which is not available with conventional methods
due to the nature of their storage. Example:

```kotlin
val collectionManager = SketchwareCollections(File(__path__))
println(collectionManager.moreblocks.getArray()) // prints all moreblocks
```

More: [Collections tutorial](https://github.com/y9neon/SketchwareManager/tree/master/tutorials/Collections).

## Implementation

#### build.gradle:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    implementation 'com.github.y9neon:SketchwareManager:stable-2.2'
}
```

#### build.gradle.kts:

```kotlin
repositories {
    maven("https://jitpack.io")
}
dependencies {
    implementation("com.github.y9neon:SketchwareManager:stable-2.2")
}
```
