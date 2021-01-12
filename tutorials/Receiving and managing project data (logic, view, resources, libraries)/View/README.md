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
Now it has only one method:
```kotlin
viewManager.getWidgets("main") // gets widgets in the MainActivity
// or if you want to get fab in MainActivity, do next:
val fab = view.getWidgets("main", "fab").single()
println(fab.name) // prints fab haha very useful example :)
```