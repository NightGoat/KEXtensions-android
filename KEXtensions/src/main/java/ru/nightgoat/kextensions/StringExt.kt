package ru.nightgoat.kextensions

import android.util.Patterns
import ru.nightgoat.kextensions.utils.constants.DateFormats
import ru.nightgoat.kextensions.utils.constants.KexConstants
import ru.nightgoat.kextensions.utils.constants.KexConstants.EMAIL_PATTERN
import ru.nightgoat.kextensions.utils.constants.KexConstants.IP_ADDRESS_PATTERN
import ru.nightgoat.kextensions.utils.constants.KexConstants.PHONE_PATTERN
import ru.nightgoat.kextensions.utils.constants.KexConstants.ZERO_STRING
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

fun String.orIfEmpty(input: () -> String): String {
    return if (this.isEmpty()) input.invoke()
    else this
}

fun String?.toDoubleOrZero() = this?.toDoubleOrNull() ?: 0.0

fun String?.toDoubleOrDefault(defaultValue: Double): Double {
    return this?.toDoubleOrNull() ?: defaultValue
}

fun String?.toIntOrZero() = this?.toIntOrNull() ?: 0

fun String?.toIntOrDefault(defaultValue: Int): Int {
    return this?.toIntOrNull() ?: defaultValue
}

fun String?.toLongOrZero() = this?.toLongOrNull() ?: 0L

fun String?.toLongOrDefault(defaultValue: Long): Long {
    return this?.toLongOrNull() ?: defaultValue
}

/**
 * returns True if string matches input symbol. Input symbol is "X" by default.
 */
fun String?.isTrue(trueSymbol: String = "X"): Boolean {
    return this == trueSymbol
}

fun String?.toBooleanFrom(trueSymbol: String = "X", falseSymbol: String = ""): Boolean? {
    return when (this) {
        trueSymbol -> true
        falseSymbol -> false
        else -> null
    }
}

fun String?.isTrueBinary() = this.isTrue("1")

fun String?.isTrueBinaryOrNull(): Boolean? {
    return this.toBooleanFrom("1", "0")
}


fun String.trimZeros() = this.trimStart('0')

fun String?.orZero() = this ?: ZERO_STRING

fun String.takeIfNotEmpty() = this.takeIf { it.isNotEmpty() }

fun String.takeIfEmpty() = this.takeIf { it.isEmpty() }

fun String?.isWhiteSpace() = this?.all { it.isWhitespace() } == true

/**
 * Returns string in normal format
 * example:
 * val foo = "HellO wOrld!"
 * val baz = foo.normalize() -> returns "Hello world!"
 * */
fun String.normalize() = this.lowercase().replaceFirstChar {
    it.titlecase(Locale.getDefault())
}

/**
 * get Enum or null Enum from String by Reflection
 * example:
 * enum class Foo {
 *     BAR,
 *     FAR
 * }
 *
 * fun doStuff() {
 *     val bar = "BAR"
 *     val enum = bar.enumValueOrNull<Foo>() -> returns BAR
 *     val baz = "NONE"
 *     val enum2 = baz.enumValueOrNull<Foo>() -> returns null
 * }
 * */
inline fun <reified T : Enum<*>> String.enumValueOrNull(): T? =
    T::class.java.enumConstants?.firstOrNull { it.name == this }

/**
 * get Enum or default value from String by Reflection
 * */
inline fun <reified T : Enum<*>> String.enumValueOrDefault(default: T): T =
    T::class.java.enumConstants?.firstOrNull { it.name == this } ?: default

fun String.isEmail(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS ?: Pattern.compile(
        EMAIL_PATTERN
    )
    return isMatchesRegex(pattern)
}

fun String.isPhone(): Boolean {
    val pattern = Patterns.PHONE ?: Pattern.compile(
        PHONE_PATTERN
    )

    return isMatchesRegex(pattern)
}

fun String.isNumeric(): Boolean {
    val pattern = KexConstants.ONLY_DIGITS_PATTERN
    return isMatchesRegex(pattern)
}

fun String.isIPAddress(): Boolean {
    val ipAddressPatternString = IP_ADDRESS_PATTERN
    val ipAddressPattern = Pattern.compile(ipAddressPatternString)
    val pattern = Patterns.IP_ADDRESS ?: ipAddressPattern
    return isMatchesRegex(pattern)
}

fun String.isMatchesRegex(regex: String): Boolean {
    return regex.toRegex().matches(this)
}

fun String.isMatchesRegex(regex: Pattern): Boolean {
    return regex.toRegex().matches(this)
}

fun String.isMatchesRegex(regex: Regex): Boolean {
    return regex.matches(this)
}

fun String.toDateFormat(): DateFormat? {
    return tryOrNull { SimpleDateFormat(this, Locale.getDefault()) }
}

fun String.toDate(pattern: String = DateFormats.DATE_FORMAT_dd_mm_yyyy): Date? {
    return tryOrNull { pattern.toDateFormat()?.parse(this) }
}

fun String.toDateOrNow(pattern: String = DateFormats.DATE_FORMAT_dd_mm_yyyy): Date {
    return tryOrNull { pattern.toDateFormat()?.parse(this) } ?: Date()
}

fun String.formatDateOrNull(
    sourcePattern: String = DateFormats.DATE_FORMAT_dd_mm_yyyy,
    destinationPattern: String = DateFormats.DATE_FORMAT_yyyyMMdd,
): String? {
    return this.toDate(sourcePattern)?.toStringFormatted(destinationPattern)
}

/**
 * Formats date string to another date string.
 * @param sourcePattern date pattern of this string
 * @param destinationPattern date pattern you want this string to be presetned it
 * or returns default value, that is this string if nothing is provided
 */
fun String.formatDate(
    sourcePattern: String = DateFormats.DATE_FORMAT_dd_mm_yyyy,
    destinationPattern: String = DateFormats.DATE_FORMAT_yyyyMMdd,
    defaultValue: String = this
): String {
    return this.formatDateOrNull(sourcePattern, destinationPattern) ?: defaultValue
}

fun String.now(pattern: String = DateFormats.DATE_FORMAT_dd_mm_yyyy): String {
    return Date().toStringFormatted(pattern)
}

fun String.equalsIgnoreCase(other: String) = this.lowercase().contentEquals(other.lowercase())

fun String.removeSpaces(): String {
    return replace(" ", "")
}

fun String.removeLineBreaks(): String {
    return replace("\n", "").replace("\r", "")
}

inline fun String.ifNotEmpty(doFun: () -> Unit) {
    if (this.isNotEmpty()) {
        doFun()
    }
}

inline fun String.ifNotBlank(doFun: () -> Unit) {
    if (this.isNotBlank()) {
        doFun()
    }
}