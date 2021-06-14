package ru.nightgoat.kextensions.utils.logging

import android.annotation.SuppressLint
import android.util.Log

object AndroidLogger : ILogger {
    @SuppressLint("LogNotTimber")
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        Log.e(tag, message, e)
    }
}