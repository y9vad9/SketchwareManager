# Project Logic
All projects (apart from new ones in which there is nothing) have a file that 
stores data about the application logic.
By `logic` we mean variables, events, moreblocks and their contents.

You can use the LogicManager that is predefined in SketchwareProject
```kotlin
val project = SketchwareProjects(_folder_).getProjects()[0] // only for example
project.logicManager // there is already defined logicManager
```
or you can define the logic file manager: 
```kotlin
val logicManager = SketchwareProjectLogicManager(_logic_file_)
```
Inside this class there are methods that control the logic of the project, let's consider events for example:
#### Get events:
```kotlin
val mainActivityEvents = logicManager.getEvents("MainActivity")
mainActivityEvents.forEach { event -> event.apply {
        println("name: $name")
        println("type: $type")
    }
}
```
#### Manage events:
```kotlin
val event = SketchwareEvent("event_name", _event_type_, /* ... and etc */)
val eventBlocks = listOf<SketchwareProjectBlock>(
    SketchwareProjectBlock( /* block info */ )
)
logicManager.addEvent("MainActivity", event, eventBlocks)
// or delete some event
logicManager.removeEvent("MainActivity", __event_name__, __target_id__)
// or just edit event logic:
logicManager.editEventLogic("MainActivity", __event_name__, __target_id__) { 
    // ArrayList<SketchwareProjectBlock> is context of lambda
    // just add some block
    add(SketchwareProjectBlock(..))
    // or just remove some block
    remove(_position_)
    // do whatever you want, it will be saved.
}
```