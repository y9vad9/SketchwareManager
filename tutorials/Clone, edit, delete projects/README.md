# Clone, edit, delete projects
Before reading this, I recommend reading the article on getting projects.
## What is covered?
This tutorial will show how to clone, edit, delete projects.

Okay, after we get the list of projects, we should try to interact with them.
Our library provides the ability to change, delete, clone projects without any problems.

It's easy to change some data in the project config:
```kotlin
val projects = SketchwareProjects("..").getProjects()[0] // get projects list
val project = projects[0] // let's change first project in the list
// let's call lambda with ProjectConfig context
project.edit {
    // there you can change all what you want
    scId = "[new_project_id]"
    myAppName = "New app name"
    scVerName = "1.0 Test version name"
    // and etc
}
```
#### Also, library gives feature to clone project very easy:
```kotlin
val manager = SketchwareProjects("..")
val project = projects[0]
project.clone(manager.nextFreeId, ProjectDestination.from(___path___)) // nextFreeId default variable which gives free id for new project
```
Or if you need change default folders to clone, for example, to make backup feature you can do next:
```kotlin
val manager = SketchwareProjects("..")
val project = projects[0]
project.clone(manager.nextFreeId, ProjectDestination(
    File("project_file_dest"), File("data_dir_dest"),
    SketchwareProjectResources(
        File("project_img_dest"),
        File("project_icons_dest"),
        File("project_fonts_dest"),
        File("project_sounds_dest")
    )
))
```
Well, or if you just need to delete the project, just do it like this:
```kotlin
project.delete()
```
And it will be erased.