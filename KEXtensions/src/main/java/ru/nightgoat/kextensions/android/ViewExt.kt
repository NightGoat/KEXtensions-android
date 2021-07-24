package ru.nightgoat.kextensions.android

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.nightgoat.kextensions.log

fun View.setVisible(visibility: Boolean = true) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun View.setVisibleGone(isGone: Boolean = true) {
    setVisible(!isGone)
}

fun View.setInvisible(invisible: Boolean = true) {
    visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

/**
 * Try to hide the keyboard and returns whether it worked
 * @author https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (e: RuntimeException) {
        e.log(tag = "hideKeyboard(): ")
    }
    return false
}