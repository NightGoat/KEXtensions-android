import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap

fun MutableLiveData<Boolean>.revert() {
    try {
        value?.let {
            value = !it
        }
    } catch (e: IllegalStateException) {
        Log.e("revert()", "posting value in background thread!", e) // TODO: 31.05.2021  
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
