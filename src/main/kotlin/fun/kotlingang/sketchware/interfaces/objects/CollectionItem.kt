package `fun`.kotlingang.sketchware.interfaces.objects

interface CollectionItem<DataModel, Reserved1Model> {
    /**
     * Contains name of the item
     */
    val name: String

    /**
     * Contains data about item. In collections it's a file name.
     */
    val data: DataModel

    /**
     * Not null only if it moreblock
     */
    val reserved1: Reserved1Model?
}
