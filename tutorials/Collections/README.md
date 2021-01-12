# Sketchware Collections

Our library provides the ability to get a list of Collections, which is not available with conventional methods due to
the nature of their storage.

Sketchware has the ability to save `moreblocks`, `blocks`, `images`, `widgets`, `fonts`. All of these collection types
are supported by the `SketchwareCollections` class.

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
val moreblocks = collections.moreblocks.getCollection() // return List<BlockBean>
// also we can add moreblock:
collections.moreblocks.addItem(_moreblock_)
// or remove some:
collections.moreblocks.removeItem(_moreblock_)
```

### Get file connected with collection

If your collection type has additional files in the data folder (everything except `widgets`, `blocks`, `moreblocks`)
use the following method to get the collection file:

```kotlin
val image = collections.images.getCollection()[0]
collections.images.getFileByName(image.data) // data variable in our case is responsible for the file name
```

## Other realizations

If you have a different architecture, or you just need to get a list from a string taken from a collection, but not in
any particular file, just do the following:

```kotlin
val arrayOfMoreblocks = BlockParser.parseAsArray(__your_string_of_collection__)
```
