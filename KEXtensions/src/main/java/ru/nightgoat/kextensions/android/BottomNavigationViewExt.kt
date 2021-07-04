package ru.nightgoat.kextensions.android

import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.setChecked(numberItemChecked: Int) {
    this.menu.getItem(numberItemChecked).isChecked = true
}