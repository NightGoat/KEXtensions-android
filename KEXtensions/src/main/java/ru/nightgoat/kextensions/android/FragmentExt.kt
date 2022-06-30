package ru.nightgoat.kextensions.android

import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowInsets
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.nightgoat.kexcore.unsafeLazy

fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this).get(clazz)
}

fun <T> Fragment.wakeUpLiveData(vararg liveData: LiveData<T>) {
    liveData.forEach {
        it.activate(viewLifecycleOwner)
    }
}

inline fun <reified T> Fragment.argument(argumentKey: String): Lazy<T?> = unsafeLazy {
    arguments?.get(argumentKey) as? T
}

inline fun <reified T> DialogFragment.argument(argumentKey: String): Lazy<T?> = unsafeLazy {
    arguments?.get(argumentKey) as? T
}

inline fun <reified T> Fragment.argumentOrDefault(argumentKey: String, defaultValue: T): Lazy<T> =
    unsafeLazy {
        arguments?.get(argumentKey) as? T ?: defaultValue
    }

inline fun <reified T> DialogFragment.argumentOrDefault(
    argumentKey: String,
    defaultValue: T
): Lazy<T> = unsafeLazy {
    arguments?.get(argumentKey) as? T ?: defaultValue
}

fun Fragment.shortToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun Fragment.shortToast(text: () -> String) {
    Toast.makeText(requireContext(), text.invoke(), Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.longToast(text: () -> String) {
    Toast.makeText(requireContext(), text.invoke(), Toast.LENGTH_LONG).show()
}

inline val Fragment.windowWidth: Int
    get() {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val metrics = requireActivity().windowManager.currentWindowMetrics
            val insets = metrics.windowInsets.getInsets(WindowInsets.Type.systemBars())
            metrics.bounds.width() - insets.left - insets.right
        } else {
            val displayMetrics = DisplayMetrics()
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
            displayMetrics.widthPixels
        }
    }

fun Fragment.showKeyboard() {
    this.view?.postDelayed({
        activity?.showKeyboard()
    }, 100L)
}

/**
 * Extension method to provide hide keyboard for [Fragment].
 */
fun Fragment.hideKeyboard() {
    activity?.hideKeyboard()
}