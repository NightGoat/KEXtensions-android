import android.util.Log

/**
* Helper nullability function
*/
inline fun <reified T> T?.orIfNull(input: () -> T): T {
  return this ?: input()
}

/**
* get Enum from String
* */
inline fun <reified T : Enum<*>> enumValueOrNull(name: String): T? =
    T::class.java.enumConstants.firstOrNull { it.name == name }

inline fun <reified T : Enum<*>> enumValueOrDefault(name: String, default: T): T =
    T::class.java.enumConstants.firstOrNull { it.name == name } ?: default

/**
* unsafe lazy delegate realisation
*/
inline fun <reified T, reified R> R.unsafeLazy(noinline init: () -> T): Lazy<T> {
  return lazy(LazyThreadSafetyMode.NONE, init)
}

fun <T, V> T?.orLet(variable: V?, letBlock: (V) -> T) = this ?: variable?.let(letBlock::invoke)

/**
* Method that simplifies logging null objects.
* Best way to use:
* fun foo() {
*   object?.let {
*     object.doWork()
*   }.logIfNull("foo(): object null")
* }
*/
fun <T : Any> T?.logIfNull(tag: String? = null, message: String): T? {
    if (this == null) {
        Log.e(tag, message)
    }
    return this
}

fun <T> T?.exists() = this != null
