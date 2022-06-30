package ru.nightgoat.kextensions.android

import android.widget.ImageView
import io.github.nightgoat.kexcore.log

fun ImageView.setIcon(iconRes: Int) {
    try {
        setImageResource(iconRes)
        val hasIcon = iconRes != 0
        if (hasIcon) {
            setVisible(hasIcon)
            isFocusable = false
        }
    } catch (e: NullPointerException) {
        e.log()
    }
}