package ru.nightgoat.kextensions.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import io.github.nightgoat.kexcore.log
import io.github.nightgoat.kexcore.logIfNull
import io.github.nightgoat.kexcore.orEmptyMutable

fun MutableLiveData<Boolean>.revert() {
    value?.let {
        tryToSetValue(!it)
    }.logIfNull("revert(): value null!")
}

fun <X, Y> LiveData<X?>.mapNotNull(func: (X) -> Y): MutableLiveData<Y> {
    return MediatorLiveData<Y>().apply {
        addSource(this@mapNotNull) { x ->
            x ?: return@addSource
            value = func(x)
        }
    }
}

inline fun <X, Y> LiveData<X>.mutableSwitchMap(
    crossinline transform: (X) -> LiveData<Y>
): MutableLiveData<Y> = this.switchMap(transform) as MutableLiveData<Y>

inline fun <X, Y> LiveData<X>.mutableMap(
    crossinline transform: (X) -> Y
): MutableLiveData<Y> = this.map(transform) as MutableLiveData<Y>

fun <T> LiveData<T>.activate(viewLifecycleOwner: LifecycleOwner) {
    this.observe(viewLifecycleOwner, {})
}

fun <T : Any> MutableLiveData<T>.tryToSetValue(newValue: T) {
    try {
        value = newValue
    } catch (e: IllegalStateException) {
        val message = "KEXception: posting value in background thread!"
        val tag = "tryToSetValue(): "
        e.log(message, tag)
        postValue(newValue)
    }
}

fun <T : Any> MutableLiveData<T>.tryToSetValue(newValue: () -> T) {
    try {
        value = newValue()
    } catch (e: IllegalStateException) {
        val message = "KEXception: posting value in background thread!"
        val tag = "tryToSetValue(): "
        e.log(message, tag)
        postValue(newValue())
    }
}

fun <O : Any> MutableLiveData<MutableList<O>>.add(value: O) {
    val changedList = this.value.orEmptyMutable()
    changedList.add(value)
    this.tryToSetValue(changedList)
}

fun <O : Any> MutableLiveData<MutableList<O>>.addAll(list: List<O>) {
    val changedList = this.value.orEmptyMutable()
    changedList.addAll(list)
    this.tryToSetValue(changedList)
}

fun <O : Any> MutableLiveData<MutableList<O>>.addAllDistinct(list: List<O>) {
    val changedList = this.value.orEmptyMutable()
    changedList.addAll(list)
    val newValue = changedList.distinct() as MutableList<O>
    this.tryToSetValue(newValue)
}

fun <O : Any> MutableLiveData<MutableList<O>>.emptyList() {
    val changedList = this.value.orEmptyMutable()
    changedList.clear()
    this.tryToSetValue(changedList)
}

fun <O : Any> MutableLiveData<MutableList<O>>.remove(value: O) {
    val changedList = this.value.orEmptyMutable()
    changedList.remove(value)
    this.tryToSetValue(changedList)
}

fun <T> LiveData<T>.isTrue() = this.value == true
fun <T> MutableLiveData<T>.isTrue() = this.value == true

fun <T> LiveData<T>.isFalse() = this.value == false
fun <T> MutableLiveData<T>.isFalse() = this.value == false
