package `fun`.kotlingang.sketchware.encryptor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object SketchwareEncryptor {
    /**
     * Sketchware encrypts everything under one static key,
     * so it is used in the [encrypt] & [decrypt] method.
     */
    private val encryptKey = "sketchwaresecure".toByteArray()

    /**
     * Decryption of the incoming [byteArray] by [encryptKey].
     * @return [ByteArray] of decrypted [byteArray].
     */
    suspend fun decrypt(byteArray: ByteArray): ByteArray = withContext(Dispatchers.Default) {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(2, SecretKeySpec(encryptKey, "AES"), IvParameterSpec(encryptKey))
        return@withContext cipher.doFinal(byteArray) ?: error("Error while decrypting string.")
    }

    /**
     * Encryption of the incoming [byteArray] by [encryptKey].
     * @return [ByteArray] of encrypted [byteArray].
     */
    suspend fun encrypt(byteArray: ByteArray) = withContext(Dispatchers.Default) {
        val cipher: Cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(1, SecretKeySpec(encryptKey, "AES"), IvParameterSpec(encryptKey))
        return@withContext cipher.doFinal(byteArray) ?: error("Error while encrypting string.")
    }

    internal suspend fun ByteArray.decrypt() = decrypt(this)
    internal suspend fun ByteArray.encrypt() = encrypt(this)

    internal suspend fun String.decrypt() = String(decrypt(this.toByteArray()))
    internal suspend fun String.encrypt() = String(encrypt(this.toByteArray()))
}