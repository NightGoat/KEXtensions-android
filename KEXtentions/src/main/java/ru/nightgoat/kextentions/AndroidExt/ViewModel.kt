import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*

fun ViewModel.launchUITryCatch(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    catchBlock: ((Throwable) -> Unit)? = null, tryBlock: suspend CoroutineScope.() -> Unit
) {
    try {
        viewModelScope.launch(viewModelScope.coroutineContext, start, tryBlock)
    } catch (e: Throwable) {
        Log.e("launchUITryCatch", "catched exception: ${e.message}", e)
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
        Log.e("launchAsyncTryCatch", "catched exception: ${e.message}", e)
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
        Log.e("asyncTryCatchLiveData", "catched exception: ${e.message}", e)
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
        Log.e("asyncTryCatchLiveData", "catched exception: ${e.message}", e)
        catchBlock?.invoke(e)
    }
} as MutableLiveData<T>
