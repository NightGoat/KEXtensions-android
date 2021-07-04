package ru.nightgoat.kextensions.android

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import ru.nightgoat.kextensions.utils.Kex

fun MutableLiveData<Boolean>.revert() {
    try {
        value?.let {
            value = !it
        }
    } catch (e: IllegalStateException) {
        Kex.loggE("KEXception: posting value in background thread!", "revert()", e)
        revertAsync()
    }
}

fun MutableLiveData<Boolean>.revertAsync() {
    value?.let {
        postValue(!it)
    }
}

inline fun <X, Y> LiveData<X>.mutableSwitchMap(
    crossinline transform: (X) -> LiveData<Y>
): MutableLiveData<Y> = this.switchMap(transform) as MutableLiveData<Y>

fun <T> LiveData<T>.activate(viewLifecycleOwner: LifecycleOwner) {
    this.observe(viewLifecycleOwner, {})
}

fun <T : Any> MutableLiveData<T>.tryToSetValue(newValue: T) {
    try {
        value = newValue
    } catch (e: IllegalStateException) {
        Kex.loggE("KEXception: posting value in background thread!", "tryToSetValue()", e)
        postValue(value)
    }
}