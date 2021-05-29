import kotlin.math.abs

fun Int?.orZero() = this ?: 0

fun Int.toNegative() = -abs(this)
