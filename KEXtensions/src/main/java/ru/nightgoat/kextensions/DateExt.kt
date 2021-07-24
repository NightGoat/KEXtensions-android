package ru.nightgoat.kextensions

import ru.nightgoat.kextensions.utils.constants.DateFormats.DATE_FORMAT_dd_mm_yyyy
import java.text.SimpleDateFormat
import java.util.*

/**
 * Sums up milliseconds in dates.
 * With that you can write something like this: Date() + Date()
 */
operator fun Date.plus(other: Date): Date = Date(this.time + other.time)
operator fun Date.minus(other: Date): Date = Date(this.time - other.time)

fun Date?.orNow() = this ?: Date()

/**
 * Returns Date in String in entered pattern. By default returns in dd.MM.yyyy
 */
fun Date.toStringFormatted(pattern: String = DATE_FORMAT_dd_mm_yyyy): String {
    return tryOrEmpty { SimpleDateFormat(pattern, Locale.getDefault()).format(this) }
}