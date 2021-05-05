package `fun`.kotlingang.sketchware.interfaces.managers

interface CollectionManager<Entity> {
    /**
     * @return list of all collection items.
     */
    val all: List<Entity>

    /**
     * Adds item to collection
     * @param entity - Entity to add.
     */
    fun addItem(entity: Entity)

    /**
     * Removes item which matches [entity].
     */
    fun removeItem(entity: Entity)

}