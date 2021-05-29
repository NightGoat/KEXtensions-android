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

/**
 * Returns string in normal format
 * example:
 * val foo = "HellO wOrld!"
 * val baz = foo.normalize() -> returns "Hello world!"
 * */
fun String.normalize() = this.lowercase().replaceFirstChar {
    it.titlecase(Locale.getDefault())
}

/**
 * get Enum or null Enum from String by Reflection
 * example:
 * enum class Foo {
 *     BAR,
 *     FAR
 * }
 *
 * fun doStuff() {
 *     val bar = "BAR"
 *     val enum = bar.enumValueOrNull<Foo>() -> returns BAR
 *     val baz = "NONE"
 *     val enum2 = baz.enumValueOrNull<Foo>() -> returns null
 * }
 * */
inline fun <reified T : Enum<*>> String.enumValueOrNull(): T? =
    T::class.java.enumConstants.firstOrNull { it.name == this }

/**
 * get Enum or default value from String by Reflection
 * */
inline fun <reified T : Enum<*>> String.enumValueOrDefault(default: T): T =
    T::class.java.enumConstants.firstOrNull { it.name == this } ?: default