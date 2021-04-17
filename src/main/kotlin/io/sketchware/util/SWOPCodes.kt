package io.sketchware.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SWOPCodes {
    private val opcodes by lazy { javaClass.getResource("/opcodes/logic")!!.readText().lines() }

    suspend fun getOpCodes() = withContext(Dispatchers.IO) {
        return@withContext opcodes
    }

}