package ru.nightgoat.kextentions.utils

import ru.nightgoat.kextentions.utils.logging.AndroidLogger
import ru.nightgoat.kextentions.utils.logging.ILogger
import ru.nightgoat.kextentions.utils.logging.TimberLogger
import timber.log.Timber

object Kex {
    var logger: ILogger = AndroidLogger

    fun setCustomLogger(newLogger: ILogger): Kex {
        logger = newLogger
        return this
    }

    fun setTimber(tree: Timber.Tree = Timber.DebugTree()): Kex {
        Timber.plant(tree)
        logger = TimberLogger
        return this
    }

    fun logE(message: String, tag: String? = null, e: Throwable? = null) {
        logger.loggE(message, tag, e)
    }
}