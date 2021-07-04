package ru.nightgoat.kextensions.android

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.nightgoat.kextensions.unsafeLazy

fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this).get(clazz)
}

fun <T> Fragment.wakeUpLiveData(liveData: LiveData<T>) {
    liveData.activate(viewLifecycleOwner)
}

fun <T> Fragment.wakeUpLiveData(listOfLD: List<LiveData<T>>) {
    listOfLD.forEach(::wakeUpLiveData)
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