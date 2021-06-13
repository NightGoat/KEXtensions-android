import android.util.Patterns
import java.util.*
import java.util.regex.Pattern

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

fun String.isEmail(): Boolean {
    val pattern = Patterns.EMAIL_ADDRESS ?: Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    return isMatchesRegex(pattern)
}

fun String.isPhone(): Boolean {
    val pattern = Patterns.PHONE ?: Pattern.compile(
        "(\\+[0-9]+[\\- \\.]*)?"
                + "(\\([0-9]+\\)[\\- \\.]*)?"
                + "([0-9][0-9\\- \\.]+[0-9])"
    )

    return isMatchesRegex(pattern)
}

fun String.isIPAddress(): Boolean {
    val ipAddressPatternString =
        ("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                + "|[1-9][0-9]|[0-9]))")
    val ipAddressPattern = Pattern.compile(ipAddressPatternString)
    val pattern = Patterns.IP_ADDRESS ?: ipAddressPattern
    return isMatchesRegex(pattern)
}

fun String.isMatchesRegex(regex: String): Boolean {
    return regex.toRegex().matches(this)
}

fun String.isMatchesRegex(regex: Pattern): Boolean {
    return regex.toRegex().matches(this)
}

fun String.isMatchesRegex(regex: Regex): Boolean {
    return regex.matches(this)
}