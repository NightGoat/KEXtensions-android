package ru.nightgoat.kextensions

/**
 * Uses sumWith from DoubleExt to avoid algebraic errors
 * @see sumWith
 */
inline fun <T> Iterable<T>.sumByDoubleSafe(selector: (T) -> Double): Double {
    var sum = 0.0
    for (element in this) {
        sum = sum.sumWith(selector(element))
    }
    return sum
}