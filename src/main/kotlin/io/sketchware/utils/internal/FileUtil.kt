package io.sketchware.utils.internal

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

internal suspend fun File.getFiles() = getFilesOrEmpty().also {
    if (it.isEmpty()) error("error while getting files")
}

internal suspend fun File.getFilesOrEmpty() = withContext(Dispatchers.IO) {
    return@withContext listFiles()?.toList() ?: emptyList()
}

internal suspend fun File.read() = withContext(Dispatchers.IO) {
    return@withContext readBytes()
}

internal suspend fun File.readOrNull() = try {
    read()
} catch (e: Exception) {
    System.err.println(e)
    null
}

internal suspend fun File.write(bytes: ByteArray) = withContext(Dispatchers.IO) {
    return@withContext writeBytes(bytes)
}

internal suspend fun File.remove() = withContext(Dispatchers.IO) {
    return@withContext if (isFile) delete() else deleteRecursively()
}

internal suspend fun removeFiles(vararg files: File) = files.forEach {
    it.remove()
}

internal suspend fun File.copy(destFile: File, overwrite: Boolean = true) = withContext(Dispatchers.IO) {
    if (isFile) {
        if (!destFile.exists()) destFile.parentFile.createFolder()
        copyTo(destFile, overwrite)
    } else {
        if (exists()) {
            if (!destFile.exists()) destFile.createFolder()
            copyRecursively(destFile, overwrite)
        } else {
            println("Folder onto $path path does not exist. Skipped.")
        }
    }
    Unit
}

internal suspend fun File.createFolder(): Boolean = withContext(Dispatchers.IO) {
    mkdirs()
}