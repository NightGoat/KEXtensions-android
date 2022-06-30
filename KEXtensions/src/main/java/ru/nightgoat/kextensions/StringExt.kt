package ru.nightgoat.kextensions

import io.github.nightgoat.kexcore.tryOrNull
import io.github.nightgoat.kexcore.utils.constants.DateFormats
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

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


fun String.isMatchesRegex(regex: Pattern): Boolean {
    return regex.toRegex().matches(this)
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