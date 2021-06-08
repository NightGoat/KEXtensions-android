package ru.nightgoat.kextentions.utils.logging

import orIfNull
import timber.log.Timber

object TimberLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        tag?.let {
            Timber.tag(it).e(e, message)
        }.orIfNull {
            Timber.e(e, message)
        }
    }
}