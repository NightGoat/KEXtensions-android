package ru.nightgoat.kextensions

import kotlin.math.abs

fun Int?.orZero() = this ?: 0

fun Int.toNegative() = -abs(this)
fun Int.toPositive() = abs(this)

fun Int.reverse() = this.unaryMinus()

fun Int.takeIfZero() = this.takeIf { it == 0 }
fun Int.takeIfNotZero() = this.takeIf { it != 0 }

fun Int?.toStringOrEmpty() = this?.toString().orEmpty()
fun Int.takeIfZeroOrEmpty() = this.takeIf { it == 0 }?.toStringOrEmpty()
