package ru.nightgoat.kextensions

import android.util.Patterns
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

fun String?.toBooleanFrom(trueSymbol: String = "1"): Boolean {
    return this == trueSymbol
}

fun String?.toBooleanFrom(trueSymbol: String, falseSymbol: String): Boolean? {
    return when (this) {
        trueSymbol -> true
        falseSymbol -> false
        else -> null
    }
}

fun String?.toBooleanFromBinary() = this.toBooleanFrom()

fun String?.toBooleanFromBinaryOrNull(): Boolean? {
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

fun String.toDate(pattern: String): Date? {
    return tryOrNull { pattern.toDateFormat()?.parse(this) }
}