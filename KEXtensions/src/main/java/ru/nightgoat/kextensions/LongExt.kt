package ru.nightgoat.kextensions

import kotlin.math.abs

fun Long?.orZero(): Long = this ?: 0L

fun Long.toNegative(): Long = -abs(this)
fun Long.toPositive(): Long = abs(this)

fun Long.reverse(): Long = this.unaryMinus()

fun Long.takeIfZero(): Long? = this.takeIf { it == 0L }
fun Long.takeIfNotZero(): Long? = this.takeIf { it != 0L }

fun Long?.toStringOrEmpty() = this?.toString().orEmpty()
fun Long.takeIfZeroOrEmpty() = this.takeIf { it == 0L }?.toStringOrEmpty()

fun Long.takeIfNotNegative() = this.takeIf { it >= 0 }

fun Long?.toDoubleOrZero() = this?.toDouble().orZero()
fun Long?.toIntOrZero() = this?.toInt().orZero()