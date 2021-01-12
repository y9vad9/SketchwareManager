# Project View
#### ⚠ be careful, this API may change at any time in future versions, use only at your own risk.
Our library provides the ability to get and change (soon) views of custom views and activities.

You can use the View Manager that is predefined in SketchwareProject
```kotlin
val project = SketchwareProjects(_folder_).getProjects()[0] // only for example
project.viewManager // there is already defined viewManager
```
or you can define the logic file manager:
```kotlin
val viewManager = SketchwareProjectViewManager(_view_file_)
```
Let's get view:
```kotlin
viewManager.getView("main") // gets widgets in the MainActivity
// or if you want, for example, get fab in MainActivity, do next:
val fab = view.getView("main", "fab").single()
println(fab.name) // prints fab haha very useful example :)
```
Or if you want to edit some view:
```kotlin
viewManager.editView("main") {
    // there ArrayList in lambda's context, do everything you want with this.
    ...
}
```