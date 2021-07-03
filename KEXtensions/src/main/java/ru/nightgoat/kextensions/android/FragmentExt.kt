package ru.nightgoat.kextensions.android

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> Fragment.provideViewModel(clazz: Class<T>): T {
    return ViewModelProvider(this).get(clazz)
}

fun <T> Fragment.wakeUpLiveData(vararg liveData: LiveData<T>) {
    liveData.forEach {
        it.activate(viewLifecycleOwner)
    }
}

fun <T> Fragment.wakeUpLiveData(listOfLD: List<LiveData<T>>) {
    listOfLD.forEach {
        it.activate(viewLifecycleOwner)
    }
}