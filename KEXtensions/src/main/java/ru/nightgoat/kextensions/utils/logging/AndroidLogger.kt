package ru.nightgoat.kextensions.utils.logging

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("LogNotTimber")
object AndroidLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        Log.e(tag, message, e)
    }

    override fun loggD(message: String, tag: String?) {
        Log.d(tag, message)
    }
}