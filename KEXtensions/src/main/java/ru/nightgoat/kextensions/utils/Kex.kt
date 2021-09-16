package ru.nightgoat.kextensions.utils

import ru.nightgoat.kextensions.utils.logging.AndroidLogger
import ru.nightgoat.kextensions.utils.logging.ILogger
import ru.nightgoat.kextensions.utils.logging.TimberLogger
import timber.log.Timber

/**
 * Main utils class
 * @author NightGoat
 * */
object Kex {
    private var logger: ILogger = AndroidLogger
    private var isLoggerOn = true
    private var isStackTraceOn: Boolean = true

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

    /**
     * Turns off this noisy logger.
     * "Will you shut up and listen to me? Shut down all garbage mashers on the Detention Level, will you? Do you copy?"
     * */
    fun turnOffLogger(): Kex {
        isLoggerOn = false
        return this
    }

    fun loggE(message: String, tag: String? = null, e: Throwable? = null) {
        if (isLoggerOn) {
            val throwable = e.takeIf { isStackTraceOn }
            logger.loggE(message, tag, throwable)
        }
    }

    fun loggD(message: String, tag: String? = null) {
        if (isLoggerOn) {
            logger.loggD(message, tag)
        }
    }
}