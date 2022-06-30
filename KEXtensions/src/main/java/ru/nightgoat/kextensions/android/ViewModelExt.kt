package ru.nightgoat.kextensions.android

import androidx.lifecycle.*
import io.github.nightgoat.kexcore.log
import kotlinx.coroutines.*

fun ViewModel.launchUITryCatch(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    catchBlock: ((Throwable) -> Unit)? = null,
    tryBlock: suspend CoroutineScope.() -> Unit
) {
    try {
        viewModelScope.launch(viewModelScope.coroutineContext, start, tryBlock)
    } catch (e: Throwable) {
        e.log(tag = "launchUITryCatch(): ")
        catchBlock?.invoke(e)
    }
}

fun ViewModel.launchAsyncTryCatch(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    catchBlock: ((Throwable) -> Unit)? = null,
    tryBlock: suspend CoroutineScope.() -> Unit
) {
    try {
        launchAsync(dispatcher, start, tryBlock)
    } catch (e: Throwable) {
        e.log(tag = "launchAsyncTryCatch(): ")
        catchBlock?.invoke(e)
    }
}

fun ViewModel.launchAsync(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(viewModelScope.coroutineContext + dispatcher, start, block)
}

inline fun <reified T> ViewModel.asyncLiveData(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    noinline block: suspend LiveDataScope<T>.() -> Unit
) = liveData(context = viewModelScope.coroutineContext + dispatcher, block = block)

fun <T> ViewModel.asyncTryCatchLiveData(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    catchBlock: ((Throwable) -> Unit)? = null,
    tryBlock: suspend () -> T
) = liveData(context = viewModelScope.coroutineContext + dispatcher) {
    try {
        emit(tryBlock())
    } catch (e: Throwable) {
        e.log(tag = "asyncTryCatchLiveData(): ")
        catchBlock?.invoke(e)
    }
}

fun <T> ViewModel.asyncTryCatchMutableLiveData(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    catchBlock: ((Throwable) -> Unit)? = null,
    tryBlock: suspend () -> T
) = liveData(context = viewModelScope.coroutineContext + dispatcher) {
    try {
        emit(tryBlock())
    } catch (e: Throwable) {
        e.log(tag = "asyncTryCatchLiveData(): ")
        catchBlock?.invoke(e)
    }
} as MutableLiveData<T>
