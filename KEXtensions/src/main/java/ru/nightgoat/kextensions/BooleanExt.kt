package ru.nightgoat.kextensions

fun Boolean?.orTrue(): Boolean = this ?: true

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean.takeIfTrue() = this.takeIf { it }

fun Boolean.takeIfFalse() = this.takeIf { !it }

fun Boolean.doIfTrue(doFun: () -> Unit): Boolean {
    if (this) {
        doFun.invoke()
    }
    return this
}

fun Boolean.doIfFalse(doFun: () -> Unit): Boolean {
    if (!this) {
        doFun.invoke()
    }
    return this
}

fun Boolean.toBinary() = 1.takeIf { this }.orZero()