# Sketchware Collections
Our library provides the ability to get a list of Collections, which is not available with conventional methods due to 
the nature of their storage.

Sketchware has the ability to save `moreblocks`, `blocks`, `images`, `widgets`, `fonts`. 
All of these collection types are supported by the `SketchwareCollections` class.
#### Let's initialize SketchwareCollections:
```kotlin
val collections = SketchwareCollections(File(__path_to_collections_folder__))
```
In this class we have `moreblocks`, `blocks`, `images`, `widgets`, `fonts` variables which auto-generated instances
for `SketchwareCollection` class.
### Sketchware Collection
Since we already have instances for all possible collections, let's try to do something with them.

Let's get array of moreblocks for example:
```kotlin
val moreblocks = collections.moreblocks.getArray() // return List<BlockBean>
```
Let's delete some moreblock and save it to sketchware collection back:
```kotlin
moreblocks.remove(0) // remove the moreblock from the array
collections.moreblocks.save(moreblocks) // save it back
```
#### ⚠ Save collections only like this to avoid errors.
### Get file connected with collection
If your collection type has additional files in the data folder (everything except `widgets`, `blocks`, `moreblocks`) use the 
following method to get the collection file:
```kotlin
val image = collections.images.getArray()[0]
collections.images.getFileByName(image.data) // data variable in our case is responsible for the file name
```
## Other realizations
If you have a different architecture, or you just need to get a list from a string taken from a collection, but not in
any particular file, just do the following:
```kotlin
val arrayOfMoreblocks = BlockBeanParser.parseAsArray(__your_string_of_collection__)
```
