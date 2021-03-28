package io.sketchware.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LogicOpCodes {
    val opcodes by lazy { javaClass.getResource("/opcodes/logic").readText().split("\n") }

    suspend fun getOpCodes() = withContext(Dispatchers.IO) {
        return@withContext opcodes
    }

}