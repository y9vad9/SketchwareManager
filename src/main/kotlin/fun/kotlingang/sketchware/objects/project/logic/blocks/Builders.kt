@file:Suppress("unused")

package `fun`.kotlingang.sketchware.objects.project.logic.blocks

import `fun`.kotlingang.sketchware.objects.project.logic.BlockProperties

/**
 * Primitives
 */
fun BooleanPrimitiveBlock(properties: BlockProperties): BooleanPrimitiveBlock = BooleanPrimitiveBlockImpl(properties)
fun NumberPrimitiveBlock(properties: BlockProperties): NumberPrimitiveBlock = NumberPrimitiveBlockImpl(properties)
fun StringPrimitiveBlock(properties: BlockProperties): StringPrimitiveBlock = StringPrimitiveBlockImpl(properties)

/**
 * Structured
 */
fun MapBlock(properties: BlockProperties): MapBlock = MapBlockImpl(properties)
fun ListMapBlock(properties: BlockProperties): ListMapBlock = ListMapBlockImpl(properties)
fun ListStringBlock(properties: BlockProperties): ListStringBlock = ListStringBlockImpl(properties)
fun ListNumberBlock(properties: BlockProperties): ListNumberBlock = ListNumberBlockImpl(properties)

/**
 * Operators
 */
fun IfStyledBlock(properties: BlockProperties, firstSubStack: List<Block> = listOf()): IfStyledBlock =
    IfStyledBlockImpl(properties, firstSubStack)

fun IfElseStyledBlock(
    properties: BlockProperties,
    firstSubStack: List<Block> = listOf(),
    secondSubStack: List<Block> = listOf()
): IfElseStyledBlock = IfElseStyledBlockImpl(properties, firstSubStack, secondSubStack)

fun StopStyledBlock(properties: BlockProperties): StopStyledBlock = StopStyledBlockImpl(properties)

/**
 * Components
 */
fun BluetoothComponentBlock(properties: BlockProperties): BluetoothComponentBlock =
    BluetoothComponentBlockImpl(properties)

fun CalendarComponentBlock(properties: BlockProperties): CalendarComponentBlock = CalendarComponentBlockImpl(properties)
fun CameraComponentBlock(properties: BlockProperties): CameraComponentBlock = CameraComponentBlockImpl(properties)
fun DialogComponentBlock(properties: BlockProperties): DialogComponentBlock = DialogComponentBlockImpl(properties)
fun FilePickerComponentBlock(properties: BlockProperties): FilePickerComponentBlock =
    FilePickerComponentBlockImpl(properties)

fun FirebaseAuthComponentBlock(properties: BlockProperties): FirebaseAuthComponentBlock =
    FirebaseAuthComponentBlockImpl(properties)

fun FirebaseDatabaseComponentBlock(properties: BlockProperties): FirebaseDatabaseComponentBlock =
    FirebaseDatabaseComponentBlockImpl(properties)

fun FirebaseStorageComponentBlock(properties: BlockProperties): FirebaseStorageComponentBlock =
    FirebaseStorageComponentBlockImpl(properties)

fun GyroscopeComponentBlock(properties: BlockProperties): GyroscopeComponentBlock =
    GyroscopeComponentBlockImpl(properties)

fun IntentComponentBlock(properties: BlockProperties): IntentComponentBlock = IntentComponentBlockImpl(properties)
fun InterstitialAdComponentBlock(properties: BlockProperties): InterstitialAdComponentBlock =
    InterstitialAdComponentBlockImpl(properties)

fun LocationManagerComponentBlock(properties: BlockProperties): LocationManagerComponentBlock =
    LocationManagerComponentBlockImpl(properties)

fun MediaPlayerComponentBlock(properties: BlockProperties): MediaPlayerComponentBlock =
    MediaPlayerComponentBlockImpl(properties)

fun ObjectAnimatorComponentBlock(properties: BlockProperties): ObjectAnimatorComponentBlock =
    ObjectAnimatorComponentBlockImpl(properties)

fun RequestNetworkComponentBlock(properties: BlockProperties): RequestNetworkComponentBlock =
    RequestNetworkComponentBlockImpl(properties)

fun SharedPreferencesComponentBlock(properties: BlockProperties): SharedPreferencesComponentBlock =
    SharedPreferencesComponentBlockImpl(properties)

fun SoundPoolComponentBlock(properties: BlockProperties): SoundPoolComponentBlock =
    SoundPoolComponentBlockImpl(properties)

fun SpeechToTextComponentBlock(properties: BlockProperties): SpeechToTextComponentBlock =
    SpeechToTextComponentBlockImpl(properties)

fun TextToSpeechComponentBlock(properties: BlockProperties): TextToSpeechComponentBlock =
    TextToSpeechComponentBlockImpl(properties)

fun TimerComponentBlock(properties: BlockProperties): TimerComponentBlock = TimerComponentBlockImpl(properties)
fun VibratorComponentBlock(properties: BlockProperties): VibratorComponentBlock = VibratorComponentBlockImpl(properties)


/**
 * Makes String spec argument.
 * @param isInputOnly - is string input only or it can contain blocks in.
 * @return [StringBlockSpecArgument].
 */
fun StringBlockSpecArgument(isInputOnly: Boolean = false): StringBlockSpecArgument =
    StringBlockSpecArgumentImpl("%s".plus(if (isInputOnly) ".inputOnly" else ""))

/**
 * Makes named String spec argument.
 * @param isInputOnly - is string input only or it can contain blocks in.
 * @param name - name of argument.
 * @return [StringBlockSpecArgument].
 */
fun NamedStringBlockSpecArgument(isInputOnly: Boolean = false, name: String): NamedStringBlockSpecArgument =
    NamedStringBlockSpecArgumentImpl("%s".plus(if (isInputOnly) ".inputOnly" else "").plus(".$name"))

/**
 * Makes Number spec argument.
 * @return [NumberBlockSpecArgumentImpl].
 */
fun NumberBlockSpecArgument(): NumberBlockSpecArgument = NumberBlockSpecArgumentImpl("%d")

/**
 * Makes Number spec argument.
 * @param name - name of the argument.
 * @return [NamedNumberBlockSpecArgument].
 */
fun NamedNumberBlockSpecArgument(name: String): NamedNumberBlockSpecArgument =
    NamedNumberBlockSpecArgumentImpl("%d.$name")

/**
 * Makes Boolean spec argument.
 * @return [NumberBlockSpecArgumentImpl].
 */
fun BooleanBlockSpecArgument(): BooleanBlockSpecArgument = BooleanBlockSpecArgumentImpl("%b")

/**
 * Makes Boolean spec argument.
 * @param name - name of the argument.
 * @return [NamedNumberBlockSpecArgument].
 */
fun NamedBooleanBlockSpecArgument(name: String): NamedBooleanBlockSpecArgument =
    NamedBooleanBlockSpecArgumentImpl("%b.$name")

/**
 * Makes menu spec argument.
 * @param menuName - menu's name in Sketchware **(not an argument name)**.
 */
fun MenuBlockSpecArgument(menuName: String): MenuBlockSpecArgument = MenuBlockSpecArgumentImpl("%m.$menuName")

/* TODO fun MenuBlockSpecArgument(menu: Menu) */

/**
 * Makes menu spec argument with name.
 * @param menuName - menu's name in Sketchware **(not an argument name)**.
 * @param argumentName - argument's name.
 */
fun NamedMenuBlockSpecArgument(menuName: String, argumentName: String): NamedMenuBlockSpecArgument =
    NamedMenuBlockSpecArgumentImpl("%m.$menuName.$argumentName")

/* TODO fun NamedMenuBlockSpecArgument(menu: Menu, argumentName: String) */