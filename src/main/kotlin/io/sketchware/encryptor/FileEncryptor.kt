package io.sketchware.encryptor

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object FileEncryptor {
    private val encryptKey by lazy { "sketchwaresecure".toByteArray() }

    suspend fun decrypt(byteArray: ByteArray) = suspendCoroutine<ByteArray> { continuation ->
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(2, SecretKeySpec(encryptKey, "AES"), IvParameterSpec(encryptKey))
        continuation.resume(cipher.doFinal(byteArray))
    }

    suspend fun encrypt(byteArray: ByteArray) = suspendCoroutine<ByteArray> { continuation ->
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(1, SecretKeySpec(encryptKey, "AES"), IvParameterSpec(encryptKey))
        continuation.resume(cipher.doFinal(byteArray))
    }

}