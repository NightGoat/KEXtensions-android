fun <T> List<T>?.orEmptyMutable(): MutableList<T> = this?.toMutableList() ?: mutableListOf()

fun <T> Set<T>?.orEmptyMutable(): MutableSet<T> = this?.toMutableSet() ?: mutableSetOf()

fun <T, K> Map<T, K>?.orEmptyMutable(): MutableMap<T, K> = this?.toMutableMap() ?: mutableMapOf()

fun <T, K> List<Pair<T, K>>?.orEmptyMutableMap(): MutableMap<T, K> = this?.toMutableMap() ?: mutableMapOf()

fun <K, V> Iterable<Pair<K, V>>.toMutableMap(): MutableMap<K, V> {
    if (this is Collection) {
        return when (size) {
            0 -> mutableMapOf()
            1 -> mutableMapOf(if (this is List) this[0] else iterator().next())
            else -> toMap(LinkedHashMap<K, V>(size))
        }
    }
    return toMap(LinkedHashMap())
}

fun <T> List<T>.indexOfOrNull(element: T) = this.indexOf(element).takeIf { it != -1 }

fun <T> MutableCollection<T>.addIf(predicate: Boolean, whatToAdd: () -> T) {
    if (predicate) this.add(whatToAdd())
}

/**
* Distincts and filter Iterable in one cycle. Faster that using
* list.distinctBy {  }.filter {  }
*/
inline fun <T, K> Iterable<T>.distinctAndFilter(distinctBy: (T) -> K, filterBy: (T) -> Boolean): List<T> {
    val set = HashSet<K>()
    val list = ArrayList<T>()
    for (e in this) {
        val key = distinctBy(e)
        if (set.add(key) && filterBy(e))
            list.add(e)
    }
    return list
}

/**
* Uses sumWith from DoubleExt to avoid algebraic errors
* @see DoubleExt
*/
inline fun <T> Iterable<T>.sumByDoubleSafe(selector: (T) -> Double): Double {
    var sum: Double = 0.0
    for (element in this) {
        sum = sum.sumWith(selector(element))
    }
    return sum
}


inline fun <reified T> Collection<T>.firstOrElse(elseFun: () -> T): T {
    return this.firstOrNull().orIfNull(elseFun)
}

fun <T> List<T>.sizeInDouble(): Double {
    return this.size.toDouble()
}

fun <T> List<T>.sizeInString(): String {
    return this.size.toString()
}

inline fun <reified R> Iterable<*>.findInInstanceOf(
    predicate: (R) -> Boolean
): R? {
    for (element in this) if (element is R && predicate(element)) return element
    return null
}

inline fun <T, K> List<T>?.mapNotNullOrEmpty(transform: (T) -> K?): List<K> =
    this.orEmpty().mapNotNull {
        transform.invoke(it)
    }

inline fun <T, K> List<T>?.mapOrEmpty(transform: (T) -> K): List<K> = this.orEmpty().map {
    transform.invoke(it)
}