package `fun`.kotlingang.sketchware.interfaces.objects

/**
 * Interface which uses for serialization enums into custom numbers.
 */
interface Identifiable {
    /**
     * Id which will be serialized/deserialized while serialization.
     */
    val id: Int
}