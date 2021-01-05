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

## Implementation

### ⚠ It's only dev version

Try not to use this version for production. The public API can change at any time and also may not be stable. Only at
your own peril and risk.

#### build.gradle:

```groovy
dependencies {
    implementation 'com.github.y9neon:Sketchware-Project-Manager:dev-2.0'
}
```

#### build.gradle.kts:

```kotlin
dependencies {
    implementation("com.github.y9neon:Sketchware-Project-Manager:dev-2.0")
}
```
