package `fun`.kotlingang.sketchware.objects.project.logic

enum class BlockType(val typeName: String) {
    REGULAR("" /* regular style has empty [typeName] */),
    IF("c"),
    IF_ELSE("e"),
    STRING("s"),
    BOOLEAN("b"),
    NUMBER("d"),
    VARIABLE("v"),
    MAP("a"),
    STOP("f"),
    LIST("l"),
    COMPONENT("p"),
    HEADER("h")
}