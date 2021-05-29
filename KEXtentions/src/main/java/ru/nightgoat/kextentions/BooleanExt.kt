fun Boolean?.orTrue(): Boolean = this ?: true

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean.takeIfTrue() = this.takeIf { it }

fun Boolean.takeIfFalse() = this.takeIf { !it }

fun Boolean.doIfTrue(doFun: () -> Unit) {
    if (this) {
        doFun.invoke()
    }
}

fun Boolean.doIfFalse(doFun: () -> Unit) {
    if (!this) {
        doFun.invoke()
    }
}
