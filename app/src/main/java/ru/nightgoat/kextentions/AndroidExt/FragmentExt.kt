import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this).get(clazz)
}
