import java.util.*

fun String.orIfEmpty(input: () -> String): String {
    return if (this.isEmpty()) input.invoke()
    else this
}

fun String?.toDoubleOrZero() = this?.toDoubleOrNull() ?: 0.0

fun String?.toDoubleOrDefault(defaultValue: Double): Double {
    return this?.toDoubleOrNull() ?: defaultValue
}

fun String?.toIntOrZero() = this?.toIntOrNull() ?: 0

fun String?.toIntOrDefault(defaultValue: Int): Int {
    return this?.toIntOrNull() ?: defaultValue
}

fun String?.toLongOrZero() = this?.toLongOrNull() ?: 0L

fun String?.toLongOrDefault(defaultValue: Long): Long {
    return this?.toLongOrNull() ?: defaultValue
}

fun String.trimZeros() = this.trimStart('0')

fun String?.orZero() = this ?: "0"

fun String.takeIfNotEmpty() = this.takeIf { it.isNotEmpty() }

fun String.takeIfEmpty() = this.takeIf { it.isEmpty() }

fun String?.isWhiteSpace() = this?.all { it.isWhitespace() } == true

fun String.normalize() = this.lowercase().replaceFirstChar {
    it.titlecase(Locale.getDefault())
}
