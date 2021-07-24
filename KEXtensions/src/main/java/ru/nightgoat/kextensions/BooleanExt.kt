package ru.nightgoat.kextensions

fun Boolean?.orTrue(): Boolean = this ?: true
fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean.takeIfTrue() = this.takeIf { it }
fun Boolean.takeIfFalse() = this.takeIf { !it }

fun Boolean.doIfTrue(doFun: (Boolean) -> Unit): Boolean {
    if (this) {
        doFun(this)
    }
    return this
}

fun Boolean.doIfFalse(doFun: (Boolean) -> Unit): Boolean {
    if (!this) {
        doFun.invoke(!this)
    }
    return this
}

/**
 * Returns 1 if true or 0
 */
fun Boolean.toBinary() = 1.takeIf { this }.orZero()

/**
 * Returns a string represented as the entered characters
 * "X" as true, and "" by default
 */
fun Boolean.toStringBy(trueSymbol: String = "X", falseSymbol: String = ""): String {
    return if (this) trueSymbol else falseSymbol
}