package ru.nightgoat.kextensions

import ru.nightgoat.kextensions.utils.Kex
import kotlin.math.pow
import kotlin.math.round

/**
 * Returns string from double without zeroes at the end
 */
fun Double?.dropZeros(): String {
    if (this == null) {
        return "0"
    }
    return if (this == this.toLong().toDouble())
        String.format("%d", this.toLong())
    else
        String.format("%s", this)
}

fun Double?.orZero(): Double {
    return this ?: 0.0
}

/**
 * Basic algebraic functions, helps to avoid Floating Point Math errors
 */
fun Double?.sumWith(other: Double?, numberOfZeroes: Int = 3): Double {
    val sum = ((this ?: 0.0).toBigDecimal() + (other ?: 0.0).toBigDecimal()).toDouble()
    return sum.roundTo(numberOfZeroes)
}

fun Double?.minWith(other: Double?, numberOfZeroes: Int = 3): Double {
    val sub = ((this ?: 0.0).toBigDecimal() - (other ?: 0.0).toBigDecimal()).toDouble()
    return sub.roundTo(numberOfZeroes)
}

fun Double.roundTo(numberOfZeroes: Int): Double {
    val divider = 10.0
    val dividerWithPow = divider.pow(numberOfZeroes)
    return round((this) * dividerWithPow) / dividerWithPow
}

fun Double.timesWith(other: Double, numberOfZeroes: Int = 3): Double {
    val pow = ((this).toBigDecimal() * (other).toBigDecimal()).toDouble()
    return pow.roundTo(numberOfZeroes)
}

fun Double.divWith(other: Double, numberOfZeroes: Int = 3, tag: String? = null): Double? {
    return if (other != 0.0) {
        val pow = ((this).toBigDecimal() / (other).toBigDecimal()).toDouble()
        pow.roundTo(numberOfZeroes)
    } else {
        Kex.loggE(
            "Division by zero: $this / 0",
            tag = tag ?: "Double.divWith(): ",
            ArithmeticException()
        )
        null
    }
}

fun Double?.toStringWithoutScientificNotation(): String {
    return this?.let {
        val isItTrueDouble = it % 1.0 != 0.0
        val newString = it.toBigDecimal().toPlainString()
        if (isItTrueDouble) {
            newString.trimEnd('0')
        } else {
            newString
        }
    }.orIfNull { "0" }
}
