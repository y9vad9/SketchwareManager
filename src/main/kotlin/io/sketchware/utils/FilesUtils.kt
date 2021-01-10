package io.sketchware.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

internal suspend fun File.writeFile(bytes: ByteArray) = withContext(Dispatchers.IO) {
    writeBytes(bytes)
}

suspend fun File.readFile(): ByteArray = withContext(Dispatchers.IO) {
    return@withContext readBytes()
}

internal suspend fun File.getListFiles(): List<File>? = withContext(Dispatchers.IO) {
    return@withContext listFiles()?.toList()
}

internal suspend fun deleteFiles(vararg files: File) = withContext(Dispatchers.IO) {
    files.forEach {
        if(it.isFile)
            it.delete()
        else it.deleteRecursively()
    }
}

internal suspend fun File.copy(destFile: File) = withContext(Dispatchers.IO) {
    copyTo(destFile, overwrite = true)
}

internal suspend fun File.copyFolder(destFile: File) = withContext(Dispatchers.IO) {
    if (exists()) {
        if (!destFile.exists()) destFile.mkdirs()
        copyRecursively(destFile, overwrite = true)
    }
}