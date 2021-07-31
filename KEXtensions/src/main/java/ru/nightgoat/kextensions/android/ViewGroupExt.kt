package ru.nightgoat.kextensions.android

import android.view.View
import android.view.ViewGroup

/**
 * Extension method to access the view's children as a list
 */
val ViewGroup.children: List<View>
    get() = (0 until childCount).map { getChildAt(it) }