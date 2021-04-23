package `fun`.kotlingang.sketchware.interfaces

/**
 * Interface which uses for serialization enums into custom numbers.
 */
interface Identifiable {
    /**
     * Id which will be serialized/deserialized while serialization.
     */
    val id: Int
}