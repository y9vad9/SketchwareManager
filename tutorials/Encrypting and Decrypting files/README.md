# Encrypting and Decrypting files
## What is covered?
This tutorial will show you how to encrypt and decrypt encrypted sketchware files using library methods.

Unfortunately, due to the fact that some implementations of sketchware architecture and data storage in files, our library may not provide some functions. You can help us in any way by making a pull request or simply decrypt files for other purposes using our library.

All is easy, our library has two methods `encrypt` and `decrypt` in `FileEncryptor` singleton.

To encrypt some string to use it in sketchware make next:
```kotlin
val stringToEncrypt = "..."
val encryptedBytes = FileEncryptor.encrypt(stringToEncrypt.toByteArray())
// and write it to file or do something what you want with it
```
To decrypt some files do next:
```kotlin
val file = File(__path__)
val bytes = file.readBytes() // read file and get bytearray of file content
val encryptedBytes = FileEncryptor.decrypt(bytes)
```