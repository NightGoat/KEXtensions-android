package ru.nightgoat.kextensions.utils.logging

import io.github.nightgoat.kexcore.orIfNull
import io.github.nightgoat.kexcore.utils.logging.ILogger
import timber.log.Timber

object TimberLogger : ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        tag?.let {
            Timber.tag(it).e(e, message)
        }.orIfNull {
            Timber.e(e, message)
        }
    }

    override fun loggD(message: String, tag: String?) {
        tag?.let {
            Timber.tag(it).d(message)
        }.orIfNull {
            Timber.d(message)
        }
    }
}