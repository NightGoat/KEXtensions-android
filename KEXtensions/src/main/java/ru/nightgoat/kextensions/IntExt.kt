package ru.nightgoat.kextensions

import ru.nightgoat.kextensions.utils.Kex
import kotlin.math.abs

fun Int?.orZero() = this ?: 0

fun Int.toNegative() = -abs(this)
fun Int.toPositive() = abs(this)

fun Int.reverse() = this.unaryMinus()

fun Int.takeIfZero() = this.takeIf { it == 0 }
fun Int.takeIfNotZero() = this.takeIf { it != 0 }

fun Int?.toStringOrEmpty() = this?.toString().orEmpty()
fun Int.takeIfZeroOrEmpty() = this.takeIf { it == 0 }?.toStringOrEmpty()

fun Int?.toBoolean() = this == 1

fun Int?.toBooleanOrNull(): Boolean? {
    return when (this) {
        1 -> true
        0 -> false
        else -> null
    }
}

fun Int?.divideSafe(divideBy: Int) = if (this != null && divideBy != 0) {
    this / divideBy
} else {
    Kex.loggE("divideSafe(): division by zero!")
    null
}

fun Int?.divideSafeOrZero(divideBy: Int) = if (this != null && divideBy != 0) {
    this / divideBy
} else {
    Kex.loggE("divideSafe(): division by zero!")
    0
}

fun Int.convertSecondsToMilliseconds(): Long = this * 1000L
