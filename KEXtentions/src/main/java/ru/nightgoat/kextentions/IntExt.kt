import kotlin.math.abs

fun Int?.orZero() = this ?: 0

fun Int.toNegative() = -abs(this)
fun Int.toPositive() = abs(this)

fun Int.reverse() = this.unaryMinus()
