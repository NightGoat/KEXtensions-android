package ru.nightgoat.kextensions.utils

import ru.nightgoat.kextensions.utils.logging.AndroidLogger
import ru.nightgoat.kextensions.utils.logging.ILogger
import ru.nightgoat.kextensions.utils.logging.TimberLogger
import timber.log.Timber

object Kex {
    var logger: ILogger = AndroidLogger
    var isStackTraceOn: Boolean = true

    fun setCustomLogger(newLogger: ILogger): Kex {
        logger = newLogger
        return this
    }

    fun setTimber(tree: Timber.Tree = Timber.DebugTree()): Kex {
        Timber.plant(tree)
        logger = TimberLogger
        return this
    }

    fun turnOffStackTrace(): Kex {
        isStackTraceOn = false
        return this
    }

    fun turnOnStackTrace(): Kex {
        isStackTraceOn = true
        return this
    }

    fun loggE(message: String, tag: String? = null, e: Throwable? = null) {
        val throwable = e.takeIf { isStackTraceOn }
        logger.loggE(message, tag, throwable)
    }
}