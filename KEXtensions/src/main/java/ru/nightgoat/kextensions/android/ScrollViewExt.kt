package ru.nightgoat.kextensions.android

import android.animation.ObjectAnimator
import android.os.Handler
import android.view.animation.LinearInterpolator
import android.widget.HorizontalScrollView
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

fun HorizontalScrollView.maxScrollRight() {
    Handler().post {
        val child = getChildAt(0) ?: return@post
        val childWidth = child.measuredWidth
        val scrollWidth = width - paddingLeft - paddingRight

        val scrollToX = childWidth - scrollWidth
        if (scrollToX > 0) {
            val animator = ObjectAnimator.ofInt(this, "scrollX", scrollToX)
            animator.interpolator = LinearInterpolator()
            animator.duration = 300
            animator.start()
        }
    }
}

fun HorizontalScrollView.maxScrollLeft() {
    val animator = ObjectAnimator.ofInt(this, "scrollX", 0)
    animator.interpolator = LinearInterpolator()
    animator.duration = 300
    animator.start()
}