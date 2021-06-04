import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import ru.nightgoat.kextentions.utils.Kex

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
