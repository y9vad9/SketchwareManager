package `fun`.kotlingang.sketchware.internal.parsers

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal object SWOpCodesParser {
    private val opcodes by lazy { javaClass.getResource("/opcodes/logic")!!.readText().lines() }

    suspend fun getOpCodes() = withContext(Dispatchers.IO) {
        return@withContext opcodes
    }
}