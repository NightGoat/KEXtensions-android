package ru.nightgoat.kextensions.android

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.showKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun EditText.disable() {
    enable(false)
}

fun EditText.enable() {
    enable(true)
}

fun EditText.enable(enabled: Boolean) {
    isFocusable = enabled
    isEnabled = enabled
    isFocusableInTouchMode = enabled
    isCursorVisible = enabled
    isClickable = enabled
    isActivated = enabled
    isCursorVisible = enabled
    setSelectAllOnFocus(enabled)
    setTextIsSelectable(enabled)
}
