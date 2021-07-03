package ru.nightgoat.kextensions.android

import android.widget.ScrollView

fun ScrollView.smoothScrollToTop() {
    smoothScrollTo(0, 0)
}

fun ScrollView.scrollToTop() {
    scrollTo(0, 0)
}

fun ScrollView.smoothScrollToBottom() {
    smoothScrollTo(this.right, this.bottom)
}

fun ScrollView.scrollToBottom() {
    scrollTo(this.right, this.bottom)
}